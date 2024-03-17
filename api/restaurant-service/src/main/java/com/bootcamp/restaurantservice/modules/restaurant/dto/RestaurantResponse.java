package com.bootcamp.restaurantservice.modules.restaurant.dto;

public record RestaurantResponse(
        String id,
        String geo,
        String name,
        String imageSrc,
        Double averageRate,
        Long totalReviewsCount
) {

}
