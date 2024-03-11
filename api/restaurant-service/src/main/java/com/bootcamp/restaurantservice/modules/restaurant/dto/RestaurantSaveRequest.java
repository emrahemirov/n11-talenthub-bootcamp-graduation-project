package com.bootcamp.restaurantservice.modules.restaurant.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record RestaurantSaveRequest(

        @NotNull
        @NotEmpty
        String latitude,
        @NotNull
        @NotEmpty
        String longitude,
        @NotNull
        @NotEmpty
        String name
) {
}
