package ru.kpfu.servlets.service;

import ru.kpfu.servlets.Repositories.ReviewDAO;
import ru.kpfu.servlets.dto.reviewDTO.ReviewRequestDTO;
import ru.kpfu.servlets.dto.reviewDTO.ReviewResponseDTO;
import ru.kpfu.servlets.models.Review;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewService {
    private ReviewDAO reviewDAO;

    public ReviewService(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public void addReview(ReviewRequestDTO reviewRequestDTO) {
        Review review = mapToEntity(reviewRequestDTO);
        reviewDAO.save(review);
    }

    public List<ReviewResponseDTO> getAllReviewsWithUserNames() {
        return reviewDAO.getReviewsWithUserName().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private Review mapToEntity(ReviewRequestDTO reviewRequestDTO) {
        return Review.builder()
                .userId(reviewRequestDTO.getUserId())
                .rating(reviewRequestDTO.getRating())
                .comment(reviewRequestDTO.getComment())
                .build();
    }

    private ReviewResponseDTO mapToResponseDTO(Review review) {
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
