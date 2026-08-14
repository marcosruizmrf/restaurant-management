package com.restaurant.management.service;

import com.restaurant.management.dto.request.CreateUserRequest;
import com.restaurant.management.dto.response.UserResponse;

public interface UserService {

    UserResponse create(CreateUserRequest request);


}
