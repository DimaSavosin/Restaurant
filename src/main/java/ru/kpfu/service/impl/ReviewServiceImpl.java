package ru.kpfu.service.impl;

import ru.kpfu.Repositories.ReviewDAO;
import ru.kpfu.dto.mapper.ReviewMapper;
import ru.kpfu.dto.reviewDTO.ReviewRequestDTO;
import ru.kpfu.dto.reviewDTO.ReviewResponseDTO;
import ru.kpfu.models.Review;
import ru.kpfu.service.ReviewService;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewServiceImpl implements ReviewService {
    private final ReviewDAO reviewDAO;

    public ReviewServiceImpl(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public void addReview(ReviewRequestDTO reviewRequestDTO) {
        Review review = ReviewMapper.toEntity(reviewRequestDTO);
        reviewDAO.save(review);
    }

    public List<ReviewResponseDTO> getAllReviewsWithUserNames() {
        return reviewDAO.getReviewsWithUserName().stream()
                .map(ReviewMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public double getAverageRating() {
        double averageRating = reviewDAO.getAverageRating();
        return Math.round(averageRating * 10.0) / 10.0;
    }

    @Override
    public void deleteReviewById(int id) {
        reviewDAO.deleteReviewById(id);
    }
}
