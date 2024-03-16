package com.bootcamp.reviewservice.modules.review.dto;

import com.bootcamp.reviewservice.modules.user.dto.UserResponse;

public record ReviewResponse(
        Long id,
        UserResponse user,
        String restaurantId,
        String comment,
        Double rate
) {

}