package com.restaurant.management.exception;

public class UserTypeNotFoundException extends RuntimeException {
    public UserTypeNotFoundException(Long id) {
        super("Tipo de usuário não encontrado com id: " + id);
    }
}
