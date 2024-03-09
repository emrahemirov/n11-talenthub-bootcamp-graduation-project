package com.bootcamp.reviewservice.modules.review.dto;

import com.bootcamp.reviewservice.modules.review.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    ReviewResponse toReviewResponse(Review entity);

    List<ReviewResponse> toReviewResponseList(List<Review> entityList);

    //    @Mapping(target = "status", constant = "INACTIVE")
    Review toReview(ReviewSaveRequest dto);

    Review toReview(ReviewUpdateRequest dto);

}