package com.restaurant.management.dto.request;

// request/CreateRestaurantRequest.java
public record CreateRestaurantRequest(
        String nome,
        String endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        Long ownerId
) {}