package com.bootcamp.reviewservice.modules.review.service;

import com.bootcamp.reviewservice.advice.responseexceptionhandler.ErrorMessage;
import com.bootcamp.reviewservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import com.bootcamp.reviewservice.modules.review.client.RestaurantFeignClient;
import com.bootcamp.reviewservice.modules.review.dto.ReviewMapper;
import com.bootcamp.reviewservice.modules.review.dto.ReviewResponse;
import com.bootcamp.reviewservice.modules.review.dto.ReviewSaveRequest;
import com.bootcamp.reviewservice.modules.review.dto.ReviewUpdateRequest;
import com.bootcamp.reviewservice.modules.review.dto.averagerate.AverageRateUpdateRequest;
import com.bootcamp.reviewservice.modules.review.model.Review;
import com.bootcamp.reviewservice.modules.review.repository.ReviewRepository;
import com.bootcamp.reviewservice.modules.user.model.User;
import com.bootcamp.reviewservice.modules.user.service.UserService;
import com.bootcamp.reviewservice.shared.QueryParams;
import com.bootcamp.reviewservice.shared.WithPagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository repository;
    private final UserService userService;
    private final RestaurantFeignClient client;

    // newRate == null -> delete
    // oldRate == null -> add
    // else -> update

    public ReviewResponse save(ReviewSaveRequest saveRequest) {
        User user = userService.findUserById(saveRequest.userId());

        client.updateAverageReview(
                saveRequest.restaurantId(),
                new AverageRateUpdateRequest(
                        saveRequest.restaurantId(), null,
                        saveRequest.rate()));

        Review reviewWithUser = ReviewMapper.INSTANCE.toReview(saveRequest);
        reviewWithUser.setUser(user);

        Review review = repository.save(reviewWithUser);
        return ReviewMapper.INSTANCE.toReviewResponse(review);
    }

    public WithPagination<ReviewResponse> findAllByRestaurantId(String restaurantId, QueryParams queryParams) {
        Page<Review> reviewPage = repository.findAllByRestaurantId(restaurantId, PageRequest.of(queryParams.getPage(), queryParams.getSize()));
        List<ReviewResponse> reviewResponseList = ReviewMapper.INSTANCE.toReviewResponseList(reviewPage.getContent());
        return WithPagination.of(reviewPage, reviewResponseList);
    }


    public void delete(Long id) {
        Review review = findReviewById(id);

        client.updateAverageReview(
                review.getRestaurantId(),
                new AverageRateUpdateRequest(
                        review.getRestaurantId(),
                        review.getRate(),
                        null));

        repository.delete(review);
    }


    public ReviewResponse update(ReviewUpdateRequest updateRequest) {
        Review review = findReviewById(updateRequest.id());
        Double oldRate = review.getRate();
        Double newRate = updateRequest.rate();

        client.updateAverageReview(
                review.getRestaurantId(),
                new AverageRateUpdateRequest(
                        review.getRestaurantId(),
                        oldRate,
                        newRate));

        ReviewMapper.INSTANCE.mutateReview(review, updateRequest);
        repository.save(review);

        return ReviewMapper.INSTANCE.toReviewResponse(review);
    }


    public Review findReviewById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(ErrorMessage.REVIEW_NOT_FOUND));
    }

}
