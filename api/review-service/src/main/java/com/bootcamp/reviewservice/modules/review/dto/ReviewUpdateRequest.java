package com.bootcamp.reviewservice.modules.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ReviewUpdateRequest(
        @NotNull
        Long id,
        String comment,
        @Min(1)
        @Max(5)
        Double rate
) {
}
