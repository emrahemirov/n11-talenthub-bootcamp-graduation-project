package com.bootcamp.reviewservice.modules.user.dto;

public record UserResponse(
        Long id,
        String username,
        String name,
        String surname
) {

}