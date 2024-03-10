package com.bootcamp.restaurantservice.modules.restaurant.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record RestaurantSaveRequest(
        @NotNull
        @Size(max = 50)
        String name,
        @NotNull
        @Size(max = 50)
        String surname
) {
}
