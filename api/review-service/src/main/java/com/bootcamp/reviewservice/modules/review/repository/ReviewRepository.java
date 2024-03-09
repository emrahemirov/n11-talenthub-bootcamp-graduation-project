package com.bootcamp.reviewservice.modules.review.repository;

import com.bootcamp.reviewservice.modules.review.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Review, Long> {
}
