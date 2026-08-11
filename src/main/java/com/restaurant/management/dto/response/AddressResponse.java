package com.restaurant.management.dto.response;

import com.restaurant.management.model.Address;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados do address")
public record AddressResponse(
        String street,
        String number,
        String city,
        String zipCode
) {
    public static AddressResponse from(Address address) {
        if (address == null) return null;
        return new AddressResponse(address.getStreet(), address.getNumber(), address.getCity(), address.getZipCode());
    }
}
