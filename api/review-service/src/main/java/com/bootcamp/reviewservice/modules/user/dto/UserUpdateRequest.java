package com.bootcamp.reviewservice.modules.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @NotNull
        Long id,
        @NotBlank
        @Size(max = 50)
        String username,
        @NotBlank
        @Size(max = 50)
        String name,
        @NotBlank
        @Size(max = 50)
        String surname
) {

}