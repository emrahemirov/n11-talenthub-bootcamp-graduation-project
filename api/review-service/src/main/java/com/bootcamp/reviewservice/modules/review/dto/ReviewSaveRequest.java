package com.bootcamp.reviewservice.modules.review.dto;

import com.bootcamp.reviewservice.modules.review.model.ReviewRate;
import jakarta.validation.constraints.NotNull;

public record ReviewSaveRequest(
        @NotNull
        Long userId,
        @NotNull
        String restaurantId,
        String comment,
        @NotNull
        ReviewRate rate
) {

}
