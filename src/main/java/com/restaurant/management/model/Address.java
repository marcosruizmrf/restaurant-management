package com.restaurant.management.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Data
public class Address {
    private String street;
    private String number;
    private String city;
    private String zipCode;
}
