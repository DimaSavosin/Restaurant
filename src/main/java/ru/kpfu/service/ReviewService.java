package ru.kpfu.service;

import ru.kpfu.dto.reviewDTO.ReviewRequestDTO;
import ru.kpfu.dto.reviewDTO.ReviewResponseDTO;

import java.util.List;

public interface ReviewService {
    void addReview(ReviewRequestDTO reviewRequestDTO);
    List<ReviewResponseDTO> getAllReviewsWithUserNames();
    double getAverageRating();
    void deleteReviewById(int id);

}
