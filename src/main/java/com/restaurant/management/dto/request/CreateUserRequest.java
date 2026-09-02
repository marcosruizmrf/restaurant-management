package com.restaurant.management.dto.request;

import com.restaurant.management.enums.UserType;
import com.restaurant.management.exception.ExceptionMessages;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para criação de um novo usuário")
public record CreateUserRequest(

        @NotBlank(message = ExceptionMessages.NAME_REQUIRED)
        @Size(max = 255, message = ExceptionMessages.NAME_MAX_LENGTH)
        @Schema(description = "Nome completo", example = "João Silva")
        String name,

        @Email(message = ExceptionMessages.EMAIL_INVALID)
        @NotBlank(message = ExceptionMessages.EMAIL_REQUIRED)
        @Size(max = 255, message = ExceptionMessages.EMAIL_MAX_LENGTH)
        @Schema(description = "E-mail único do usuário", example = "joao.novo@email.com")
        String email,

        @NotBlank(message = ExceptionMessages.LOGIN_REQUIRED)
        @Size(max = 255, message = ExceptionMessages.LOGIN_MAX_LENGTH)
        @Schema(description = "Login de acesso", example = "joaosilva")
        String login,

        @NotBlank(message = ExceptionMessages.PASSWORD_REQUIRED)
        @Size(min = 6, max = 72, message = ExceptionMessages.PASSWORD_LENGTH)
        @Schema(description = "Senha de acesso (mínimo 6, máximo 72 caracteres)", example = "senha123")
        String password,

        @NotNull(message = ExceptionMessages.TYPE_REQUIRED)
        @Schema(description = "Tipo do usuário", example = "CLIENT", allowableValues = {"CLIENT", "RESTAURANT_OWNER"})
        UserType userType,

        @Valid
        @Schema(description = "Endereço do usuário")
        AddressRequest address
) {}
