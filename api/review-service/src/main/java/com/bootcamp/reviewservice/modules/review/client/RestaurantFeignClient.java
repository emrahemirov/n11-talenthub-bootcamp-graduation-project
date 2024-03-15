package com.bootcamp.reviewservice.modules.review.client;

import com.bootcamp.reviewservice.config.FeignConfig;
import com.bootcamp.reviewservice.modules.review.dto.averagerate.AverageRateUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "restaurantClient", url = "RESTAURANT-SERVICE" + "/api/restaurants", configuration = FeignConfig.class)
public interface RestaurantFeignClient {

    @PutMapping("/{debugId}/average-review")
    public void updateAverageReview(@PathVariable String debugId, @RequestBody AverageRateUpdateRequest updateRequest);
}