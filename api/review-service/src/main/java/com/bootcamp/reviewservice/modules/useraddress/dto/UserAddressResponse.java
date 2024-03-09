package com.bootcamp.reviewservice.modules.useraddress.dto;

import com.bootcamp.reviewservice.modules.useraddress.model.UserAddressType;

public record UserAddressResponse(
        Long id,
        String name,
        String latitude,
        String longitude,
        String addressLine,
        String buildingNumber,
        String floor,
        String apartmentNumber,
        UserAddressType addressType
) {

}
