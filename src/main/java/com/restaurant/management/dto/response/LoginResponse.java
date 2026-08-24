package com.restaurant.management.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta da validação de login")
public record LoginResponse(
        boolean valid,
        String message,
        Long userId,
        String type
) {}
