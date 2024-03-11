package com.bootcamp.restaurantservice.modules.restaurant.dto;

public record AverageRateUpdateRequest(
        String id,
        ReviewRate rate,
        AverageRateUpdateType type) {
}
