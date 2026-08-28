package com.restaurant.management.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import com.restaurant.management.exception.ExceptionMessages;

@Schema(description = "Credenciais para validação de login")
public record LoginRequest(

        @NotBlank(message = ExceptionMessages.LOGIN_REQUIRED)
        @Schema(description = "Login do usuário", example = "joaosilva")
        String login,

        @NotBlank(message = ExceptionMessages.PASSWORD_REQUIRED)
        @Schema(description = "Senha do usuário", example = "senha123")
        String password
) {}
