package ru.kpfu.Repositories;


import ru.kpfu.models.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewDAO extends CrudDAO<Review> {
    List<Review> getReviewsWithUserName();
    double getAverageRating();
    void deleteReviewById(int id);
}
