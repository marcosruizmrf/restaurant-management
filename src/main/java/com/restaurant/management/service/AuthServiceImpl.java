package com.restaurant.management.service;

import com.restaurant.management.dto.request.CreateUserRequest;
import com.restaurant.management.dto.request.UpdateUserRequest;
import com.restaurant.management.dto.response.UserResponse;
import com.restaurant.management.factory.UserFactory;
import com.restaurant.management.model.User;
import com.restaurant.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;

    @Override
    public UserResponse create(CreateUserRequest request) {
        User user = userFactory.createEntity(request);
        return UserResponse.from(userRepository.save(user));
    }

    @Override
    public UserResponse update(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        user.setName(request.name());
        user.setEmail(request.email());
        if (request.address() != null) {
            user.setAddress(userFactory.toAddress(request.address()));
        }
        user.setLastChange(LocalDateTime.now());

        return UserResponse.from(userRepository.save(user));
    }
}
