package com.bootcamp.restaurantservice.modules.restaurant.dto.averagerate;

public record AverageRateUpdateRequest(
        String id,
        ReviewRate rate,
        AverageRateUpdateType type) {
}
