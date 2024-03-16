package com.bootcamp.reviewservice.modules.user.dto;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String username,
        String name,
        String surname,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}