package ru.kpfu.service.impl;

import ru.kpfu.Repositories.ReviewDAO;
import ru.kpfu.dto.reviewDTO.ReviewRequestDTO;
import ru.kpfu.dto.reviewDTO.ReviewResponseDTO;
import ru.kpfu.models.Review;
import ru.kpfu.service.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewServiceImpl implements ReviewService {
    private ReviewDAO reviewDAO;

    public ReviewServiceImpl(ReviewDAO reviewDAO) {
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

    @Override
    public double getAverageRating() {
        return reviewDAO.getAverageRating();
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
