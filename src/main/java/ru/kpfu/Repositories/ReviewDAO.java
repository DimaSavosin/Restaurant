package ru.kpfu.Repositories;


import ru.kpfu.models.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewDAO extends CrudDAO<Review> {
    Optional<Review> getReviewsById(int id);
    List<Review> getReviewsByUserId(int userId);
    List<Review> getReviewsWithUserName();
    double getAverageRating();
}
