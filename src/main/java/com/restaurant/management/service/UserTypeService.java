package com.restaurant.management.service;

import com.restaurant.management.dto.request.CreateUserTypeRequest;
import com.restaurant.management.dto.request.UpdateUserTypeRequest;
import com.restaurant.management.dto.response.UserTypeResponse;

import java.util.List;

public interface UserTypeService {

    UserTypeResponse create(CreateUserTypeRequest request);

    UserTypeResponse update(Long id, UpdateUserTypeRequest request);

    void delete(Long id);

    UserTypeResponse findById(Long id);

    List<UserTypeResponse> findAll();
}
