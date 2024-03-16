package com.bootcamp.reviewservice.modules.review.dto;

import com.bootcamp.reviewservice.modules.user.dto.UserResponse;

import java.time.LocalDateTime;

public record ReviewResponse(
        Long id,
        UserResponse user,
        String restaurantId,
        String comment,
        Double rate,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}