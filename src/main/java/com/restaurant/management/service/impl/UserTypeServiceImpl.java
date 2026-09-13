package com.restaurant.management.service.impl;

import com.restaurant.management.dto.request.CreateUserTypeRequest;
import com.restaurant.management.dto.request.UpdateUserTypeRequest;
import com.restaurant.management.dto.response.UserTypeResponse;
import com.restaurant.management.exception.UserTypeAlreadyExistsException;
import com.restaurant.management.exception.UserTypeNotFoundException;
import com.restaurant.management.model.UserType;
import com.restaurant.management.repository.UserTypeRepository;
import com.restaurant.management.service.UserTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeRepository repository;

    @Override
    @Transactional
    public UserTypeResponse create(CreateUserTypeRequest request) {
        String normalizedName = request.name().trim();
        if (repository.existsByNameIgnoreCase(normalizedName)) {
            throw new UserTypeAlreadyExistsException(normalizedName);
        }

        UserType userType = new UserType();
        userType.setName(normalizedName);
        userType.setObservation(request.observation() != null ? request.observation().trim() : null);
        return UserTypeResponse.from(repository.save(userType));
    }

    @Override
    @Transactional
    public UserTypeResponse update(Long id, UpdateUserTypeRequest request) {
        UserType userType = repository.findById(id)
                .orElseThrow(() -> new UserTypeNotFoundException(id));

        String normalizedName = request.name().trim();
        if (!userType.getName().equalsIgnoreCase(normalizedName)
                && repository.existsByNameIgnoreCase(normalizedName)) {
            throw new UserTypeAlreadyExistsException(normalizedName);
        }

        userType.setName(normalizedName);
        userType.setObservation(request.observation() != null ? request.observation().trim() : null);
        return UserTypeResponse.from(repository.save(userType));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new UserTypeNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public UserTypeResponse findById(Long id) {
        return UserTypeResponse.from(repository.findById(id)
                .orElseThrow(() -> new UserTypeNotFoundException(id)));
    }

    @Override
    public List<UserTypeResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(UserTypeResponse::from)
                .toList();
    }
}
