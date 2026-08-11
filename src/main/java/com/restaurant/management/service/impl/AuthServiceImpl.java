package com.restaurant.management.service.impl;

import com.restaurant.management.dto.request.CreateUserRequest;
import com.restaurant.management.dto.response.UserResponse;
import com.restaurant.management.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements UserService {
    @Override
    public UserResponse create(CreateUserRequest request) {
        return null;
    }
}
