package com.bootcamp.restaurantservice.mock;

public record MockRestaurant(
        String id,
        String name,
        Double averageRate,
        Double latitude,
        Double longitude,
        Integer totalReviewsCount) {
}
