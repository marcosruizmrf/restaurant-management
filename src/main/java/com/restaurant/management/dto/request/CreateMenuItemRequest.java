package com.restaurant.management.dto.request;

// CreateMenuItemRequest.java
package management.dto.request;

public record CreateMenuItemRequest(
        String nome,
        String descricao,
        Double preco,
        Boolean disponivelNoLocal,
        String fotoPath,
        Long restaurantId
) {}
