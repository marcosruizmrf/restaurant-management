package com.restaurant.management.factory;

import com.restaurant.management.dto.request.AddressRequest;
import com.restaurant.management.dto.request.CreateUserRequest;
import com.restaurant.management.enums.UserType;
import com.restaurant.management.model.Address;
import com.restaurant.management.model.Client;
import com.restaurant.management.model.RestaurantOwner;
import com.restaurant.management.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserFactory {

    public User createEntity(CreateUserRequest request) {
        User user = createInstance(request.userType());
        user.setName(request.name());
        user.setEmail(request.email());
        user.setLogin(request.login());
        user.setPassword(request.password());
        user.setLastChange(LocalDateTime.now());

        if (request.address() != null) {
            user.setAddress(toAddress(request.address()));
        }

        return user;
    }

    private User createInstance(UserType userType) {
        return switch (userType) {
            case RESTAURANT_OWNER -> new RestaurantOwner();
            case CLIENT -> new Client();
        };
    }

    public Address toAddress(AddressRequest request) {
        if (request == null) {
            return null;
        }
        Address address = new Address();
        address.setStreet(request.street());
        address.setNumber(request.number());
        address.setCity(request.city());
        address.setZipCode(request.zipCode());
        return address;
    }
}
