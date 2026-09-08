package com.restaurant.management.dto.request;

import com.restaurant.management.exception.ExceptionMessages;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para criação de um novo tipo de usuário")
public record CreateUserTypeRequest(

        @NotBlank(message = ExceptionMessages.USER_TYPE_NAME_REQUIRED)
        @Size(max = 255, message = ExceptionMessages.USER_TYPE_NAME_MAX_LENGTH)
        @Schema(description = "Nome do tipo de usuário", example = "Cliente")
        String name
) {}
