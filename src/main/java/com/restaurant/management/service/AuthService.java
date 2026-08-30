package com.restaurant.management.service;

import com.restaurant.management.dto.request.LoginRequest;
import com.restaurant.management.dto.response.LoginResponse;

public interface AuthService {

    LoginResponse validateLogin(LoginRequest request);
}
