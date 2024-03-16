package com.bootcamp.restaurantservice.modules.restaurant.dto;


import javax.validation.constraints.NotBlank;

public record RestaurantSaveRequest(


        @NotBlank
        String latitude,

        @NotBlank
        String longitude,

        @NotBlank
        String name
) {
}
