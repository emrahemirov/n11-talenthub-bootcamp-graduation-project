package com.bootcamp.reviewservice.modules.review.client;

import com.bootcamp.reviewservice.config.feign.FeignConfig;
import com.bootcamp.reviewservice.modules.review.dto.averagerate.AverageRateUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "restaurantClient", url = "${restaurant-service.url}" + "/api/restaurants", configuration = FeignConfig.class)
public interface RestaurantFeignClient {

    @PatchMapping("/{debugId}/average-review")
    public ResponseEntity<HttpStatus> updateAverageReview(@PathVariable String debugId, @RequestBody AverageRateUpdateRequest updateRequest);
}