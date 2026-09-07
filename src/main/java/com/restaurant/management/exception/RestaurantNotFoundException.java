package com.restaurant.management.exception;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Long id) {
        super("Restaurante não encontrado com id: " + id);
    }
}
