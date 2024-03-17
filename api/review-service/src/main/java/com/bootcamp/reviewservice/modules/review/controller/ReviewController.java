package com.bootcamp.reviewservice.modules.review.controller;

import com.bootcamp.reviewservice.modules.review.dto.ReviewResponse;
import com.bootcamp.reviewservice.modules.review.dto.ReviewSaveRequest;
import com.bootcamp.reviewservice.modules.review.dto.ReviewUpdateRequest;
import com.bootcamp.reviewservice.modules.review.service.ReviewService;
import com.bootcamp.reviewservice.shared.QueryParams;
import com.bootcamp.reviewservice.shared.RestResponse;
import com.bootcamp.reviewservice.shared.WithPagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
@Tag(name = "Review Controller", description = "Review Management")
public class ReviewController {

    private final ReviewService service;

    @PostMapping
    @Operation(summary = "Create a new review",
            description = "Create a new review into the system. Requires review details.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Review created successfully",
                            content = @Content(schema = @Schema(implementation = ReviewResponse.class)))

            })
    public ResponseEntity<RestResponse<ReviewResponse>> create(@Valid @RequestBody ReviewSaveRequest saveRequest) {
        ReviewResponse reviewResponse = service.save(saveRequest);
        return new ResponseEntity<>(RestResponse.of(reviewResponse), HttpStatus.CREATED);
    }

    @GetMapping("/by-restaurant-id/{restaurantId}")
    @Operation(summary = "Fetches a review by its ID",
            description = "Get review details for a given review ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Review got successfully",
                            content = @Content(schema = @Schema(implementation = ReviewResponse.class)))

            })
    public ResponseEntity<RestResponse<WithPagination<ReviewResponse>>> findAllByRestaurantId(QueryParams queryParams, @PathVariable String restaurantId) {
        WithPagination<ReviewResponse> responseWithPagination = service.findAllByRestaurantId(restaurantId, queryParams);
        return new ResponseEntity<>(RestResponse.of(responseWithPagination), HttpStatus.OK);
    }


    @PutMapping("/{debugId}")
    @Operation(summary = "Updates a review by its ID",
            description = "Updates review details for a given review ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Review updated successfully",
                            content = @Content(schema = @Schema(implementation = ReviewResponse.class)))
            })
    public ResponseEntity<RestResponse<ReviewResponse>> update(@PathVariable Long debugId, @Valid @RequestBody ReviewUpdateRequest updateRequest) {
        ReviewResponse reviewResponse = service.update(updateRequest);
        return new ResponseEntity<>(RestResponse.of(reviewResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a review by its ID",
            description = "Removes a review from the system based on the review ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Review deleted successfully")
            })
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
