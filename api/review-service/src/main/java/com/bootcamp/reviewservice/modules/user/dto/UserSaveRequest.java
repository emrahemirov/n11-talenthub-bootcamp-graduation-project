package com.bootcamp.reviewservice.modules.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserSaveRequest(
        @NotNull
        @Size(max = 50)
        String name,
        @NotNull
        @Size(max = 50)
        String surname
) {
}
