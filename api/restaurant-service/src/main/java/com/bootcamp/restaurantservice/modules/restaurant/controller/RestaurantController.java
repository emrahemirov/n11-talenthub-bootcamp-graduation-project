package com.bootcamp.restaurantservice.modules.restaurant.controller;

import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantResponse;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantSaveRequest;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantUpdateRequest;
import com.bootcamp.restaurantservice.modules.restaurant.service.RestaurantService;
import com.bootcamp.restaurantservice.shared.RestResponse;
import com.bootcamp.restaurantservice.shared.WithPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<RestResponse<WithPagination<RestaurantResponse>>> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size
    ) {
        WithPagination<RestaurantResponse> responseWithPagination = service.findAll(page, size);
        return new ResponseEntity<>(RestResponse.of(responseWithPagination), HttpStatus.OK);
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
