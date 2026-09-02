package com.restaurant.management.service.impl;

import com.restaurant.management.dto.request.CreateUserRequest;
import com.restaurant.management.dto.request.UpdatePasswordRequest;
import com.restaurant.management.dto.request.UpdateUserRequest;
import com.restaurant.management.dto.response.UserResponse;
import com.restaurant.management.exception.EmailAlreadyExistsException;
import com.restaurant.management.exception.ExceptionMessages;
import com.restaurant.management.exception.InvalidPasswordException;
import com.restaurant.management.exception.UserNotFoundException;
import com.restaurant.management.factory.UserFactory;
import com.restaurant.management.model.User;
import com.restaurant.management.repository.UserRepository;
import com.restaurant.management.service.PasswordService;
import com.restaurant.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserFactory userFactory;
    private final PasswordService passwordService;

    @Override
    @Transactional
    public UserResponse create(CreateUserRequest request) {
        validateEmailUniqueness(request.email());
        User user = userFactory.createEntity(request);
        user.setPassword(passwordService.hash(request.password()));
        return UserResponse.from(repository.save(user));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        repository.delete(user);
    }

    @Override
    public List<UserResponse> findAll(String name) {
        if (name != null && !name.isBlank()) {
            return repository.findByNameContainingIgnoreCase(name)
                    .stream()
                    .map(UserResponse::from)
                    .toList();
        }
        return repository.findAll()
                .stream()
                .map(UserResponse::from)
                .toList();
    }

    @Override
    public UserResponse findById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return UserResponse.from(user);
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UpdateUserRequest request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        validateEmailUpdate(user.getEmail(), request.email());

        user.setName(request.name());
        user.setEmail(request.email());
        if (request.address() != null) {
            user.setAddress(userFactory.toAddress(request.address()));
        }
        user.setLastChange(LocalDateTime.now());

        return UserResponse.from(repository.save(user));
    }

    @Override
    @Transactional
    public void updatePassword(Long id, UpdatePasswordRequest request) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (!passwordService.matches(request.actualPassword(), user.getPassword())) {
            throw new InvalidPasswordException(ExceptionMessages.INVALID_CURRENT_PASSWORD);
        }

        if (passwordService.matches(request.newPassword(), user.getPassword())) {
            throw new InvalidPasswordException(ExceptionMessages.NEW_PASSWORD_SAME_AS_CURRENT);
        }

        user.setPassword(passwordService.hash(request.newPassword()));
        user.setLastChange(LocalDateTime.now());
        repository.save(user);
    }

    private void validateEmailUpdate(String currentEmail, String newEmail) {
        if (!currentEmail.equalsIgnoreCase(newEmail)) {
            validateEmailUniqueness(newEmail);
        }
    }

    private void validateEmailUniqueness(String email) {
        if (repository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }
    }
}
