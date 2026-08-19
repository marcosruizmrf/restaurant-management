package com.restaurant.management.service;

import com.restaurant.management.dto.request.CreateUserRequest;
import com.restaurant.management.dto.response.UserResponse;
import java.util.List;

public interface UserService {

    UserResponse create(CreateUserRequest request);

    UserResponse findById(Long id);

    List<UserResponse> findAll(String name);
}
