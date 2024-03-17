package com.bootcamp.reviewservice.modules.review.service;

import com.bootcamp.reviewservice.advice.responseexceptionhandler.exceptions.ItemNotFoundException;
import com.bootcamp.reviewservice.modules.review.client.RestaurantFeignClient;
import com.bootcamp.reviewservice.modules.review.dto.ReviewResponse;
import com.bootcamp.reviewservice.modules.review.dto.ReviewUpdateRequest;
import com.bootcamp.reviewservice.modules.review.model.Review;
import com.bootcamp.reviewservice.modules.review.repository.ReviewRepository;
import com.bootcamp.reviewservice.modules.user.model.User;
import com.bootcamp.reviewservice.modules.user.service.UserService;
import com.bootcamp.reviewservice.shared.QueryParams;
import com.bootcamp.reviewservice.shared.WithPagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository mockRepository;
    @Mock
    private UserService mockUserService;
    @Mock
    private RestaurantFeignClient mockClient;

    private ReviewService reviewServiceUnderTest;

    @BeforeEach
    void setUp() {
        reviewServiceUnderTest = new ReviewService(mockRepository, mockUserService, mockClient);
    }

    @Test
    void shouldFindAllByRestaurantId() {
        QueryParams queryParams = new QueryParams();
        queryParams.setPage(1);
        queryParams.setSize(1);

        Review review = new Review();
        review.setId(1L);
        review.setRestaurantId("restaurantId");
        review.setRate(1.0);
        review.setComment("comment");
        User user = new User();
        review.setUser(user);
        Page<Review> reviews = new PageImpl<>(List.of(review));
        when(mockRepository.findAllByRestaurantId("restaurantId", PageRequest.of(1, 1))).thenReturn(reviews);

        WithPagination<ReviewResponse> result = reviewServiceUnderTest.findAllByRestaurantId("restaurantId",
                queryParams);

    }

    @Test
    void shouldFindAllByRestaurantId_ReviewRepositoryReturnsNoItems() {
        QueryParams queryParams = new QueryParams();
        queryParams.setPage(1);
        queryParams.setSize(1);

        when(mockRepository.findAllByRestaurantId("restaurantId", PageRequest.of(1, 1)))
                .thenReturn(new PageImpl<>(Collections.emptyList()));

        WithPagination<ReviewResponse> result = reviewServiceUnderTest.findAllByRestaurantId("restaurantId",
                queryParams);

    }

    @Test
    void shouldDelete() {
        Review review1 = new Review();
        review1.setId(1L);
        review1.setRestaurantId("restaurantId");
        review1.setRate(2.0);
        review1.setComment("comment");
        User user = new User();
        review1.setUser(user);
        Optional<Review> review = Optional.of(review1);
        when(mockRepository.findById(1L)).thenReturn(review);

        reviewServiceUnderTest.delete(1L);

        verify(mockRepository).delete(any(Review.class));
    }

    @Test
    void shouldDelete_ReviewRepositoryFindByIdReturnsAbsent() {
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> reviewServiceUnderTest.delete(0L)).isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void shouldUpdate_ReviewRepositoryFindByIdReturnsAbsent() {
        ReviewUpdateRequest updateRequest = new ReviewUpdateRequest(0L, "comment", 0.0);
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> reviewServiceUnderTest.update(updateRequest))
                .isInstanceOf(ItemNotFoundException.class);
    }

    @Test
    void shouldFindReviewById() {
        Review review1 = new Review();
        review1.setId(0L);
        review1.setRestaurantId("restaurantId");
        review1.setRate(0.0);
        review1.setComment("comment");
        User user = new User();
        review1.setUser(user);
        Optional<Review> review = Optional.of(review1);
        when(mockRepository.findById(0L)).thenReturn(review);

        Review result = reviewServiceUnderTest.findReviewById(0L);
    }

    @Test
    void shouldFindReviewById_ReviewRepositoryReturnsAbsent() {
        when(mockRepository.findById(0L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> reviewServiceUnderTest.findReviewById(0L)).isInstanceOf(ItemNotFoundException.class);
    }
}