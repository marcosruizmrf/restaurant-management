package com.restaurant.management.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Credenciais para validacao de login")
public record LoginRequest(

        @NotBlank(message = "Login e obrigatorio")
        @Schema(description = "Login do usuario", example = "joaosilva")
        String login,

        @NotBlank(message = "Senha e obrigatoria")
        @Schema(description = "Senha do usuario", example = "senha123")
        String password
) {}
