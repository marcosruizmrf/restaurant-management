package com.restaurant.management.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para atualizacao das informacoes do usuario (exceto password)")
public record UpdateUserRequest(

        @NotBlank(message = "Nome e obrigatorio")
        @Schema(description = "Nome completo", example = "Joao Silva Atualizado")
        String name,

        @Email(message = "E-mail invalido")
        @NotBlank(message = "E-mail e obrigatorio")
        @Schema(description = "E-mail unico do usuario", example = "joao.novo@email.com")
        String email,

        @Valid
        @Schema(description = "Endereco do usuario")
        AddressRequest address
) {}
