package com.restaurant.management.service.impl;

import com.restaurant.management.dto.request.LoginRequest;
import com.restaurant.management.dto.response.LoginResponse;
import com.restaurant.management.exception.ExceptionMessages;
import com.restaurant.management.model.User;
import com.restaurant.management.repository.UserRepository;
import com.restaurant.management.service.AuthService;
import com.restaurant.management.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final PasswordService passwordService;

    @Override
    public LoginResponse validateLogin(LoginRequest request) {
        return repository.findByLogin(request.login())
                .filter(user -> passwordService.matches(request.password(), user.getPassword()))
                .map(this::toSuccessResponse)
                .orElseGet(this::toFailureResponse);
    }

    private LoginResponse toSuccessResponse(User user) {
        return new LoginResponse(
                true,
                ExceptionMessages.LOGIN_SUCCESS,
                user.getId(),
                user.getClass().getSimpleName()
        );
    }

    private LoginResponse toFailureResponse() {
        return new LoginResponse(
                false,
                ExceptionMessages.INVALID_LOGIN_CREDENTIALS,
                null,
                null
        );
    }
}
