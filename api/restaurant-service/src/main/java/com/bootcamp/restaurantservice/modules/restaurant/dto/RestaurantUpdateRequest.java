package com.bootcamp.restaurantservice.modules.restaurant.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record RestaurantUpdateRequest(
        @NotNull
        String id,
        @NotNull
        @Size(max = 50)
        String name,
        @NotNull
        @Size(max = 50)
        String surname
) {

}