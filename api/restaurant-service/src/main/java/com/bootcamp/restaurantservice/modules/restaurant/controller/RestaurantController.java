package com.bootcamp.restaurantservice.modules.restaurant.controller;

import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantResponse;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantSaveRequest;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantUpdateRequest;
import com.bootcamp.restaurantservice.modules.restaurant.dto.averagerate.AverageRateUpdateRequest;
import com.bootcamp.restaurantservice.modules.restaurant.service.RestaurantService;
import com.bootcamp.restaurantservice.shared.QueryParams;
import com.bootcamp.restaurantservice.shared.RestResponse;
import com.bootcamp.restaurantservice.shared.WithPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService service;

    @PostMapping
    public ResponseEntity<RestResponse<RestaurantResponse>> save(@RequestBody RestaurantSaveRequest saveRequest) {
        RestaurantResponse restaurant = service.save(saveRequest);
        return new ResponseEntity<>(RestResponse.of(restaurant), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RestResponse<WithPagination<RestaurantResponse>>> findAll(QueryParams queryParams) {
        WithPagination<RestaurantResponse> responseWithPagination = service.findAll(queryParams);
        return new ResponseEntity<>(RestResponse.of(responseWithPagination), HttpStatus.OK);
    }

    @GetMapping("/recommended")
    public ResponseEntity<RestResponse<List<RestaurantResponse>>> getRecommendedRestaurantsWithin10Km(@RequestParam String latitude, @RequestParam String longitude) {
        List<RestaurantResponse> recommendedRestaurants = service.getRecommendedRestaurantsWithin10Km(latitude, longitude);
        return new ResponseEntity<>(RestResponse.of(recommendedRestaurants), HttpStatus.OK);
    }

    @PatchMapping("/{debugId}/average-review")
    public ResponseEntity<HttpStatus> updateAverageReview(@PathVariable String debugId, @RequestBody AverageRateUpdateRequest updateRequest) {
        service.updateAverageRate(updateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{debugId}")
    public ResponseEntity<RestResponse<RestaurantResponse>> update(@PathVariable String debugId, @Valid @RequestBody RestaurantUpdateRequest updateRequest) {
        RestaurantResponse restaurantResponse = service.update(updateRequest);

        return new ResponseEntity<>(RestResponse.of(restaurantResponse), HttpStatus.OK);
    }
}
