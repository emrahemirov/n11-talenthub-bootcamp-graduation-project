package com.bootcamp.reviewservice.modules.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewSaveRequest(
        @NotNull
        Long userId,
        @NotBlank
        String restaurantId,
        @Min(1)
        @Max(5)
        Double rate,
        String comment
) {

}
