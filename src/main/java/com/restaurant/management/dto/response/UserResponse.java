package com.restaurant.management.dto.response;

import com.restaurant.management.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Dados de resposta do usuário")
public record UserResponse(
        Long id,
        String name,
        String email,
        String login,
        String userType,
        LocalDateTime lastChange,
        AddressResponse address
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getLogin(),
                user.getClass().getSimpleName(),
                user.getLastChange(),
                AddressResponse.from(user.getAddress())
        );
    }
}
