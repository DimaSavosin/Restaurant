package ru.kpfu.servlets.Repositories;


import ru.kpfu.servlets.models.Reviews;

import java.util.Optional;

public interface ReviewsDAO extends CrudDAO<Reviews> {
    Optional<Reviews> getReviewsById(int id);
}
