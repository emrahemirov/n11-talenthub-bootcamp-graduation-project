package com.bootcamp.reviewservice.modules.review.dto;

import com.bootcamp.reviewservice.modules.review.model.ReviewRate;
import jakarta.validation.constraints.NotNull;

public record ReviewUpdateRequest(
        @NotNull
        Long id,
        String comment,
        @NotNull
        ReviewRate rate
) {
}
