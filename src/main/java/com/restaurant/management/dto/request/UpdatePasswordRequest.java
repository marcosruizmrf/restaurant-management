package com.restaurant.management.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import com.restaurant.management.exception.ExceptionMessages;

@Schema(description = "Dados para alteração de senha")
public record UpdatePasswordRequest(

        @NotBlank(message = ExceptionMessages.CURRENT_PASSWORD_REQUIRED)
        @Schema(description = "Senha atual do usuário", example = "senhaAntiga123")
        String actualPassword,

        @NotBlank(message = ExceptionMessages.NEW_PASSWORD_REQUIRED)
        @Schema(description = "Nova senha do usuário", example = "senhaNova456")
        String newPassword
) {
}
