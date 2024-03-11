package com.bootcamp.restaurantservice.modules.restaurant.dto.useraddress;

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