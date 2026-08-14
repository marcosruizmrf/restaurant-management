package com.restaurant.management.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super("E-mail ja cadastrado: " + email);
    }
}
