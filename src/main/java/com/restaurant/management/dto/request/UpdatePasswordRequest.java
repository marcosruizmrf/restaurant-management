package com.restaurant.management.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.restaurant.management.exception.ExceptionMessages;

@Schema(description = "Dados para alteração de senha")
public record UpdatePasswordRequest(

        @NotBlank(message = ExceptionMessages.CURRENT_PASSWORD_REQUIRED)
        @Schema(description = "Senha atual do usuário", example = "senhaAntiga123")
        String actualPassword,

        @NotBlank(message = ExceptionMessages.NEW_PASSWORD_REQUIRED)
        @Size(min = 6, max = 72, message = ExceptionMessages.NEW_PASSWORD_LENGTH)
        @Schema(description = "Nova senha (mínimo 6, máximo 72 caracteres)", example = "senhaNova456")
        String newPassword
) {
}
