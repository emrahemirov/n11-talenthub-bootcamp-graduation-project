package com.bootcamp.restaurantservice.modules.restaurant.controller;

import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantResponse;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantSaveRequest;
import com.bootcamp.restaurantservice.modules.restaurant.dto.RestaurantUpdateRequest;
import com.bootcamp.restaurantservice.modules.restaurant.dto.averagerate.AverageRateUpdateRequest;
import com.bootcamp.restaurantservice.modules.restaurant.service.RestaurantService;
import com.bootcamp.restaurantservice.shared.QueryParams;
import com.bootcamp.restaurantservice.shared.RestResponse;
import com.bootcamp.restaurantservice.shared.WithPagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
@Tag(name = "Restaurant Controller", description = "Restaurant Management")
public class RestaurantController {
    private final RestaurantService service;

    @PostMapping
    @Operation(summary = "Creates a new restaurant",
            description = "Saves a new restaurant into the system. Requires restaurant details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Restaurant created successfully",
                            content = @Content(schema = @Schema(implementation = RestaurantResponse.class)))
            })
    public ResponseEntity<RestResponse<RestaurantResponse>> save(@Valid @RequestBody RestaurantSaveRequest saveRequest) {
        RestaurantResponse restaurant = service.save(saveRequest);
        return new ResponseEntity<>(RestResponse.of(restaurant), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Fetches all restaurants",
            description = "Get all restaurants stored in the system.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "All restaurants get successfully",
                            content = @Content(schema = @Schema(implementation = RestaurantResponse.class)))
            })
    public ResponseEntity<RestResponse<WithPagination<RestaurantResponse>>> findAll(QueryParams queryParams) {
        WithPagination<RestaurantResponse> responseWithPagination = service.findAll(queryParams);
        return new ResponseEntity<>(RestResponse.of(responseWithPagination), HttpStatus.OK);
    }

    @GetMapping("/recommended")
    @Operation(summary = "Gets recommended restaurants by user ID",
            description = "Fetches a list of recommended restaurants based on the user's location.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recommended restaurants got successfully",
                            content = @Content(schema = @Schema(implementation = RestaurantResponse.class)))
            })
    public ResponseEntity<RestResponse<List<RestaurantResponse>>> getRecommendedRestaurantsWithin10Km(@RequestParam Long userId) {
        List<RestaurantResponse> recommendedRestaurants = service.getRecommendedRestaurantsWithin10Km(userId);

        return new ResponseEntity<>(RestResponse.of(recommendedRestaurants), HttpStatus.OK);
    }

    @PutMapping("/{debugId}/average-review")
    @Operation(summary = "Updates the average score of a restaurant",
            description = "Updates the average score of a restaurant based on user reviews.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Restaurant average score updated successfully",
                            content = @Content(schema = @Schema(implementation = RestaurantResponse.class)))
            })
    public ResponseEntity<HttpStatus> updateAverageReview(@PathVariable String debugId, @Valid @RequestBody AverageRateUpdateRequest updateRequest) {
        service.updateAverageRate(updateRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a restaurant by its ID",
            description = "Removes a restaurant from the system based on the restaurant ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Restaurant deleted successfully")
            })
    public ResponseEntity<HttpStatus> delete(@PathVariable String id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{debugId}")
    @Operation(summary = "Updates a restaurant's information by its ID",
            description = "Updates restaurant details for a given restaurant ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Restaurant updated successfully",
                            content = @Content(schema = @Schema(implementation = RestaurantResponse.class)))
            })
    public ResponseEntity<RestResponse<RestaurantResponse>> update(@PathVariable String debugId, @Valid @RequestBody RestaurantUpdateRequest updateRequest) {
        RestaurantResponse restaurantResponse = service.update(updateRequest);

        return new ResponseEntity<>(RestResponse.of(restaurantResponse), HttpStatus.OK);
    }
}
