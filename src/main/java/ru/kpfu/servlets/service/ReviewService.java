package ru.kpfu.servlets.service;

import ru.kpfu.servlets.Repositories.ReviewDAO;
import ru.kpfu.servlets.models.Review;

import java.util.List;

public class ReviewService {
    private ReviewDAO reviewDAO;

    public ReviewService(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public void addReview(Review review) {
        reviewDAO.save(review);
    }


    public List<Review> getReviewsForUser(int userId) {
        return reviewDAO.getReviewsByUserId(userId);
    }

    public List<Review> getAllReviews() {
        return reviewDAO.getAll();
    }

    public List<Review> getAllReviewsWithUserNames() {
        return reviewDAO.getReviewsWithUserName();
    }


}
