package com.bootcamp.reviewservice.modules.review.controller;

import com.bootcamp.reviewservice.modules.review.dto.ReviewResponse;
import com.bootcamp.reviewservice.modules.review.dto.ReviewSaveRequest;
import com.bootcamp.reviewservice.modules.review.dto.ReviewUpdateRequest;
import com.bootcamp.reviewservice.modules.review.service.ReviewService;
import com.bootcamp.reviewservice.shared.QueryParams;
import com.bootcamp.reviewservice.shared.RestResponse;
import com.bootcamp.reviewservice.shared.WithPagination;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService service;

    @PostMapping
    public ResponseEntity<RestResponse<ReviewResponse>> create(@Valid @RequestBody ReviewSaveRequest saveRequest) {
        ReviewResponse reviewResponse = service.save(saveRequest);
        return new ResponseEntity<>(RestResponse.of(reviewResponse), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RestResponse<WithPagination<ReviewResponse>>> findAll(
            @RequestParam(required = false) QueryParams queryParams
    ) {
        WithPagination<ReviewResponse> responseWithPagination = service.findAll(queryParams);
        return new ResponseEntity<>(RestResponse.of(responseWithPagination), HttpStatus.OK);
    }


    @PutMapping("/{debugId}")
    public ResponseEntity<RestResponse<ReviewResponse>> update(@PathVariable Long debugId, @Valid @RequestBody ReviewUpdateRequest updateRequest) {
        ReviewResponse reviewResponse = service.update(updateRequest);
        return new ResponseEntity<>(RestResponse.of(reviewResponse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
