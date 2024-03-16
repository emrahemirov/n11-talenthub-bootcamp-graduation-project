package com.bootcamp.restaurantservice.modules.restaurant.dto;


import javax.validation.constraints.NotBlank;

public record RestaurantUpdateRequest(
        @NotBlank
        String id,
        @NotBlank
        String name,
        @NotBlank
        String latitude,
        @NotBlank
        String longitude
) {

}