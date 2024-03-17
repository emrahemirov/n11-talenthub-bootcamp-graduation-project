package com.bootcamp.reviewservice.modules.useraddress.dto;

import com.bootcamp.reviewservice.modules.useraddress.model.UserAddressType;

import java.time.LocalDateTime;

public record UserAddressResponse(
        Long id,
        String name,
        String latitude,
        String longitude,
        String addressLine,
        String buildingNumber,
        String floor,
        String apartmentNumber,
        Boolean isPreferred,
        UserAddressType addressType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}
