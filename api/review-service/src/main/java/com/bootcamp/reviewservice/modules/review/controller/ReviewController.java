package com.bootcamp.reviewservice.modules.review.controller;

import com.bootcamp.reviewservice.modules.review.dto.ReviewResponse;
import com.bootcamp.reviewservice.modules.review.dto.ReviewSaveRequest;
import com.bootcamp.reviewservice.modules.review.dto.ReviewUpdateRequest;
import com.bootcamp.reviewservice.modules.review.service.ReviewService;
import com.bootcamp.reviewservice.shared.RestResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<RestResponse<List<ReviewResponse>>> getAll() {
        List<ReviewResponse> reviewResponseList = service.findAll();
        return new ResponseEntity<>(RestResponse.of(reviewResponseList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<ReviewResponse>> getOne(@PathVariable Long id) {
        ReviewResponse reviewResponse = service.findById(id);
        return new ResponseEntity<>(RestResponse.of(reviewResponse), HttpStatus.OK);
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

    @PatchMapping("/{id}/activate")
    public ResponseEntity<RestResponse<ReviewResponse>> activate(@PathVariable Long id) {
        ReviewResponse reviewResponse = service.activate(id);
        return new ResponseEntity<>(RestResponse.of(reviewResponse), HttpStatus.OK);
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<RestResponse<ReviewResponse>> deactivate(@PathVariable Long id) {
        ReviewResponse reviewResponse = service.deactivate(id);
        return new ResponseEntity<>(RestResponse.of(reviewResponse), HttpStatus.OK);
    }
}
