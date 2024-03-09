package com.bootcamp.reviewservice.modules.useraddress.dto;

import com.bootcamp.reviewservice.modules.useraddress.model.UserAddressType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserAddressSaveRequest(

        @NotNull
        @Size(max = 50)
        String name,
        @NotNull
        @Size(max = 10)
        String latitude,
        @NotNull
        @Size(max = 10)
        String longitude,
        @NotNull
        @Size(max = 255)
        String addressLine,
        @NotNull
        @Size(max = 10)
        String buildingNumber,
        @Size(max = 10)
        String floor,
        @Size(max = 10)
        String apartmentNumber,
        @NotNull
        UserAddressType addressType,
        @NotNull
        Long userId
) {

}
