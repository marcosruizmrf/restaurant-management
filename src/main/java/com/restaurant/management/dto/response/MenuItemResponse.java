package com.restaurant.management.dto.response;

// MenuItemResponse.java
package management.dto.response;

public record MenuItemResponse(
        Long id,
        String nome,
        String descricao,
        Double preco,
        Boolean disponivelNoLocal,
        String fotoPath,
        Long restaurantId
) {}