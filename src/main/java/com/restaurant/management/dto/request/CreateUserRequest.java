package com.restaurant.management.dto.request;

import com.restaurant.management.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados para criacao de um novo usuario")
public record CreateUserRequest(

        @NotBlank(message = "Nome e obrigatorio")
        @Size(max = 255, message = "Nome deve ter no maximo 255 caracteres")
        @Schema(description = "Nome completo", example = "Joao Silva")
        String name,

        @Email(message = "E-mail invalido")
        @NotBlank(message = "E-mail e obrigatorio")
        @Size(max = 255, message = "E-mail deve ter no maximo 255 caracteres")
        @Schema(description = "E-mail unico do usuario", example = "joao@email.com")
        String email,

        @NotBlank(message = "Login e obrigatorio")
        @Size(max = 255, message = "Login deve ter no maximo 255 caracteres")
        @Schema(description = "Login de acesso", example = "joaosilva")
        String login,

        @NotBlank(message = "Senha e obrigatoria")
        @Size(max = 255, message = "Senha deve ter no maximo 255 caracteres")
        @Schema(description = "Senha de acesso", example = "senha123")
        String password,

        @NotNull(message = "Tipo e obrigatorio")
        @Schema(description = "Tipo do usuario: CLIENT ou RESTAURANT_OWNER", example = "CLIENT")
        UserType userType,

        @Valid
        @Schema(description = "Endereco do usuario")
        AddressRequest address
) {}
