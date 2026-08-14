package com.restaurant.management.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("RESTAURANT_OWNER")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class RestaurantOwner extends User {
}
