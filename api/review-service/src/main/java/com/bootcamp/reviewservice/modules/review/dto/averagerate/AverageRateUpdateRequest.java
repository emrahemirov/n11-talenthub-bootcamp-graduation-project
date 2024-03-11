package com.bootcamp.reviewservice.modules.review.dto.averagerate;

import com.bootcamp.reviewservice.modules.review.model.ReviewRate;

public record AverageRateUpdateRequest(
        String id,
        ReviewRate oldRate,
        ReviewRate newRate
) {
}
