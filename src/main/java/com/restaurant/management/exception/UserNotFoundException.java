package com.restaurant.management.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Usuario nao encontrado com id: " + id);
    }
}
