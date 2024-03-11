package com.bootcamp.restaurantservice.modules.restaurant.dto;


public record RestaurantUpdateRequest(
        String id,
        String latitude,
        String longitude
) {

}