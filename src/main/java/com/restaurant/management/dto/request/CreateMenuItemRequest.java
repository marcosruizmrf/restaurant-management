package com.restaurant.management.dto.request;

// CreateMenuItemRequest.java

public record CreateMenuItemRequest(
        String nome,
        String descricao,
        Double preco,
        Boolean disponivelNoLocal,
        String fotoPath,
        Long restaurantId
) {}
