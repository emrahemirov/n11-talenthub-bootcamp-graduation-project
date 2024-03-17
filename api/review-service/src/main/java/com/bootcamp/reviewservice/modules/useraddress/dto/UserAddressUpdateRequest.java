package com.bootcamp.reviewservice.modules.useraddress.dto;

import com.bootcamp.reviewservice.modules.useraddress.model.UserAddressType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record UserAddressUpdateRequest(
        @NotNull
        Long id,

        @Size(max = 50)
        String name,

        @Size(max = 10)
        String latitude,

        @Size(max = 10)
        String longitude,

        @Size(max = 255)
        String addressLine,

        @Size(max = 10)
        String buildingNumber,
        @Size(max = 10)
        String floor,
        @Size(max = 10)
        String apartmentNumber,
        
        UserAddressType addressType
) {

}