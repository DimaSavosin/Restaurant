package ru.kpfu.dto.mapper;


import ru.kpfu.dto.reviewDTO.ReviewRequestDTO;
import ru.kpfu.dto.reviewDTO.ReviewResponseDTO;
import ru.kpfu.models.Review;

public class ReviewMapper {

    public static Review toEntity(ReviewRequestDTO reviewRequestDTO) {
        return Review.builder()
                .userId(reviewRequestDTO.getUserId())
                .rating(reviewRequestDTO.getRating())
                .comment(reviewRequestDTO.getComment())
                .build();
    }

    public static ReviewResponseDTO toResponseDTO(Review review) {
        return ReviewResponseDTO.builder()
                .id(review.getId())
                .userId(review.getUserId())
                .userName(review.getUserName())
                .rating(review.getRating())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .build();
    }
}

