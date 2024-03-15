package com.bootcamp.restaurantservice.modules.restaurant.client;

import com.bootcamp.restaurantservice.modules.restaurant.config.feign.FeignConfig;
import com.bootcamp.restaurantservice.modules.restaurant.dto.useraddress.UserAddressResponse;
import com.bootcamp.restaurantservice.shared.RestResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "userClient", url = "REVIEW-SERVICE" + "/api/user-addresses", configuration = FeignConfig.class)
public interface UserAddressFeignClient {

    @GetMapping("/preferred")
    ResponseEntity<RestResponse<UserAddressResponse>> getPreferredUserAddress(@RequestParam Long userId);
}