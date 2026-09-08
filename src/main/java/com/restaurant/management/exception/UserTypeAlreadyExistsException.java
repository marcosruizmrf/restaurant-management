package com.restaurant.management.exception;

public class UserTypeAlreadyExistsException extends RuntimeException {
    public UserTypeAlreadyExistsException(String name) {
        super("Tipo de usuário já cadastrado: " + name);
    }
}
