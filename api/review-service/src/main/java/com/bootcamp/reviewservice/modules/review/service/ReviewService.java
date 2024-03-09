package com.bootcamp.reviewservice.modules.review.service;

import com.bootcamp.reviewservice.advice.responseexceptionhandler.ErrorMessage;
import com.bootcamp.reviewservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import com.bootcamp.reviewservice.modules.review.dto.ReviewMapper;
import com.bootcamp.reviewservice.modules.review.dto.ReviewResponse;
import com.bootcamp.reviewservice.modules.review.dto.ReviewSaveRequest;
import com.bootcamp.reviewservice.modules.review.dto.ReviewUpdateRequest;
import com.bootcamp.reviewservice.modules.review.model.Review;
import com.bootcamp.reviewservice.modules.review.model.ReviewStatus;
import com.bootcamp.reviewservice.modules.review.repository.ReviewRepository;
import com.bootcamp.reviewservice.modules.user.model.User;
import com.bootcamp.reviewservice.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;
    private final UserService userService;


    public ReviewResponse save(ReviewSaveRequest saveRequest) {
        User user = userService.findUserById(saveRequest.userId());
        Review reviewWithUser = ReviewMapper.INSTANCE.toReview(saveRequest);
        reviewWithUser.setUser(user);

        Review review = repository.save(reviewWithUser);
        return ReviewMapper.INSTANCE.toReviewResponse(review);
    }

    public List<ReviewResponse> findAll() {
        List<Review> reviews = repository.findAll();
        return ReviewMapper.INSTANCE.toReviewResponseList(reviews);
    }

    public ReviewResponse findById(Long id) {
        Review review = findReviewById(id);
        return ReviewMapper.INSTANCE.toReviewResponse(review);
    }

    public void delete(Long id) {
        Review review = findReviewById(id);
        repository.delete(review);
    }


    public ReviewResponse update(ReviewUpdateRequest updateRequest) {
        Review currentReview = findReviewById(updateRequest.id());
        Review reviewToUpdate = ReviewMapper.INSTANCE.toReview(updateRequest);
        reviewToUpdate.setUser(currentReview.getUser());
        reviewToUpdate.setRestaurantId(currentReview.getRestaurantId());

        Review updatedReview = repository.save(reviewToUpdate);
        return ReviewMapper.INSTANCE.toReviewResponse(updatedReview);
    }

    public ReviewResponse activate(Long id) {
        Review updatedReview = updateStatus(id, ReviewStatus.ACTIVE);

        return ReviewMapper.INSTANCE.toReviewResponse(updatedReview);
    }

    public ReviewResponse deactivate(Long id) {
        Review updatedReview = updateStatus(id, ReviewStatus.INACTIVE);

        return ReviewMapper.INSTANCE.toReviewResponse(updatedReview);
    }

    private Review updateStatus(Long id, ReviewStatus status) {
        Review foundReview = findReviewById(id);
        foundReview.setStatus(status);
        return repository.save(foundReview);
    }


    public Review findReviewById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(ErrorMessage.REVIEW_NOT_FOUND));
    }

}
