package com.bootcamp.reviewservice.modules.review.dto.averagerate;

public record AverageRateUpdateRequest(
        String restaurantId,
        Double oldRate,
        Double newRate
) {
}
