package com.restaurant.management.service;

import com.restaurant.management.dto.request.CreateUserRequest;
import com.restaurant.management.dto.request.UpdateUserRequest;
import com.restaurant.management.dto.response.UserResponse;
import com.restaurant.management.model.Address;
import com.restaurant.management.model.User;
import com.restaurant.management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse create(CreateUserRequest request) {
        return null;
    }

    @Override
    public UserResponse update(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        user.setName(request.name());
        user.setEmail(request.email());
        if (request.address() != null) {
            Address address = new Address();
            address.setStreet(request.address().street());
            address.setNumber(request.address().number());
            address.setCity(request.address().city());
            address.setZipCode(request.address().zipCode());
            user.setAddress(address);
        }
        user.setLastChange(LocalDateTime.now());

        return UserResponse.from(userRepository.save(user));
    }
}
