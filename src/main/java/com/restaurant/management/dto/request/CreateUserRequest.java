package com.restaurant.management.dto.request;

import com.restaurant.management.enums.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Dados para criacao de um novo usuario")
public record CreateUserRequest(

        @NotBlank(message = "Nome e obrigatorio")
        @Schema(description = "Nome completo", example = "Joao Silva")
        String name,

        @Email(message = "E-mail invalido")
        @NotBlank(message = "E-mail e obrigatorio")
        @Schema(description = "E-mail unico do usuario", example = "joao@email.com")
        String email,

        @NotBlank(message = "Login e obrigatorio")
        @Schema(description = "Login de acesso", example = "joaosilva")
        String login,

        @NotBlank(message = "Senha e obrigatoria")
        @Schema(description = "Senha de acesso", example = "senha123")
        String password,

        @NotNull(message = "Tipo e obrigatorio")
        @Schema(description = "Tipo do usuario: CLIENTE ou DONO_RESTAURANTE", example = "CLIENTE")
        UserType userType,

        @Valid
        @Schema(description = "Endereco do usuario")
        AddressRequest address
) {}
