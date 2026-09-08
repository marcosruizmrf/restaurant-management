package com.restaurant.management.dto.response;

import com.restaurant.management.model.UserType;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados de resposta do tipo de usuário")
public record UserTypeResponse(

        @Schema(description = "ID do tipo de usuário", example = "1")
        Long id,

        @Schema(description = "Nome do tipo de usuário", example = "Cliente")
        String name
) {
    public static UserTypeResponse from(UserType userType) {
        if (userType == null) return null;
        return new UserTypeResponse(userType.getId(), userType.getName());
    }
}
