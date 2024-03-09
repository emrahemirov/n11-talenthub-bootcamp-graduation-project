package com.bootcamp.reviewservice.modules.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
        @NotNull
        Long id,
        @NotNull
        @Size(max = 50)
        String name,
        @NotNull
        @Size(max = 50)
        String surname
) {

}