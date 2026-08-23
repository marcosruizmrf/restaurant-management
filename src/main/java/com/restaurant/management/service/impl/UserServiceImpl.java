package com.restaurant.management.service.impl;

import com.restaurant.management.dto.request.AddressRequest;
import com.restaurant.management.dto.request.CreateUserRequest;
import com.restaurant.management.dto.response.UserResponse;
import com.restaurant.management.enums.UserType;
import com.restaurant.management.exception.EmailAlreadyExistsException;
import com.restaurant.management.exception.UserNotFoundException;
import com.restaurant.management.model.Address;
import com.restaurant.management.model.Client;
import com.restaurant.management.model.RestaurantOwner;
import com.restaurant.management.model.User;
import com.restaurant.management.repository.UserRepository;
import com.restaurant.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    @Transactional
    public UserResponse create(CreateUserRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new EmailAlreadyExistsException(request.email());
        }
        User user = buildUser(request);
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

    private User buildUser(CreateUserRequest request) {
        Address address = toAddress(request.address());

        User.UserBuilder<?, ?> builder;
        if (request.userType() == UserType.CLIENT) {
            builder = Client.builder();
        } else if (request.userType() == UserType.RESTAURANT_OWNER) {
            builder = RestaurantOwner.builder();
        } else {
            throw new IllegalArgumentException("Tipo de usuario invalido: " + request.userType());
        }

        return builder
                .name(request.name())
                .email(request.email())
                .login(request.login())
                .password(request.password())
                .address(address)
                .build();
    }

    private Address toAddress(AddressRequest addressRequest) {
        if (Objects.isNull(addressRequest)) return null;
        return new Address(addressRequest.street(), addressRequest.number(), addressRequest.city(), addressRequest.zipCode());
    }
}
