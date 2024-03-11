package com.bootcamp.restaurantservice.modules.restaurant.dto.averagerate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record AverageRateUpdateRequest(

        @NotNull
        @NotEmpty
        String restaurantId,
        
        ReviewRate oldRate,
        ReviewRate newRate) {
}
