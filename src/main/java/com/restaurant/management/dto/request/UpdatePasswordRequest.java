package com.restaurant.management.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para alteracao de password")
public record UpdatePasswordRequest(

        @NotBlank(message = "Senha atual e obrigatoria")
        @Schema(description = "Senha atual do usuario", example = "senhaAntiga123")
        String actualPassword,

        @NotBlank(message = "Nova password e obrigatoria")
        @Schema(description = "Nova password do usuario", example = "senhaNova456")
        String newPassword
) {
}
