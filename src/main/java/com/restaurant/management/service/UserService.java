package com.restaurant.management.service;

import com.restaurant.management.dto.request.CreateUserRequest;
import com.restaurant.management.dto.request.UpdatePasswordRequest;
import com.restaurant.management.dto.request.UpdateUserRequest;
import com.restaurant.management.dto.response.UserResponse;
import java.util.List;

public interface UserService {

    UserResponse create(CreateUserRequest request);

    UserResponse update(Long id, UpdateUserRequest request);

    void updatePassword(Long id, UpdatePasswordRequest request);

    void delete(Long id);

    UserResponse findById(Long id);

    UserResponse assignType(Long userId, Long userTypeId);

    List<UserResponse> findAll(String name);
}
