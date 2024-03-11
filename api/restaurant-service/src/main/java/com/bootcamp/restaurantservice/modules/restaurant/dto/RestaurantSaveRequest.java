package com.bootcamp.restaurantservice.modules.restaurant.dto;


public record RestaurantSaveRequest(
        String latitude,
        String longitude,
        String name
) {
}
