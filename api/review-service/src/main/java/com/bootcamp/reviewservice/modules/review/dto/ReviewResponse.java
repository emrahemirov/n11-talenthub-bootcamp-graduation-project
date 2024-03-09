package com.bootcamp.reviewservice.modules.review.dto;

import com.bootcamp.reviewservice.modules.review.model.ReviewRate;
import com.bootcamp.reviewservice.modules.user.dto.UserResponse;

public record ReviewResponse(
        Long id,
        UserResponse user,
        Long restaurantId,
        String comment,
        ReviewRate rate
) {

}