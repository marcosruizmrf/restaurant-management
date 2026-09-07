package com.restaurant.management.dto.response;

// response/RestaurantResponse.java
public record RestaurantResponse(
        Long id,
        String nome,
        String endereco,
        String tipoCozinha,
        String horarioFuncionamento,
        Long ownerId
) {}