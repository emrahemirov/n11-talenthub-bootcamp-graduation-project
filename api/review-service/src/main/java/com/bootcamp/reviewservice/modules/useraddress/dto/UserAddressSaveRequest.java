package com.bootcamp.reviewservice.modules.useraddress.dto;

import com.bootcamp.reviewservice.modules.review.model.ReviewRate;

public record UserAddressSaveRequest(
        Long userId,
        Long restaurantId,
        String comment,
        ReviewRate rate
) {

}
