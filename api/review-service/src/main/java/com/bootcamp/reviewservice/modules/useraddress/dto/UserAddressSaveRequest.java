package com.bootcamp.reviewservice.modules.useraddress.dto;

import com.bootcamp.reviewservice.modules.useraddress.model.UserAddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserAddressSaveRequest(

        @NotBlank
        @Size(max = 50)
        String name,
        @NotBlank
        @Size(max = 10)
        String latitude,
        @NotBlank
        @Size(max = 10)
        String longitude,
        @NotBlank
        @Size(max = 255)
        String addressLine,
        @NotBlank
        @Size(max = 10)
        String buildingNumber,
        @Size(max = 10)
        String floor,
        @Size(max = 10)
        String apartmentNumber,
        @NotBlank
        UserAddressType addressType,
        @NotNull
        Long userId
) {

}
