package com.bootcamp.restaurantservice.modules.restaurant.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record RestaurantUpdateRequest(
        @NotNull
        @NotEmpty
        String id,
        String latitude,
        String longitude
) {

}