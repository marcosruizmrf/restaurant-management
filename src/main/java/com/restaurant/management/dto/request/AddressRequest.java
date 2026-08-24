package com.restaurant.management.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados do endereço")
public record AddressRequest(
        String street,
        String number,
        String city,
        String zipCode
) {}
