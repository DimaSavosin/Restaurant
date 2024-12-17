package ru.kpfu.servlets.Repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.servlets.Repositories.ReviewsDAO;
import ru.kpfu.servlets.models.Reviews;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
public class ReviewsDAOImpl implements ReviewsDAO {
    private final DataSource dataSource;


    private static final String INSERT_REVIEW_SQL = "INSERT INTO reviews (rating, comment, created) VALUES (?, ?, ?)";
    private static final String DELETE_REVIEW_SQL = "DELETE FROM reviews WHERE id = ?";
    private static final String UPDATE_REVIEW_SQL = "UPDATE reviews SET rating = ?, comment = ?, created = ? WHERE id = ?";
    private static final String SELECT_REVIEWS_SQL = "SELECT id, rating, comment, created FROM reviews";
    private static final String SELECT_REVIEWS_BY_ID_SQL = "select * from reviews where id = ?";


    @Override
    public void save(Reviews review) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REVIEW_SQL)) {

            preparedStatement.setInt(1, review.getRating());
            preparedStatement.setString(2, review.getComment());
            preparedStatement.setObject(3, review.getCreatedAt());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Reviews review) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REVIEW_SQL)) {

            preparedStatement.setInt(1, review.getRating());
            preparedStatement.setString(2, review.getComment());

            preparedStatement.setObject(3, review.getCreatedAt());
            preparedStatement.setInt(4, review.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Reviews review) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REVIEW_SQL)) {

            preparedStatement.setInt(1, review.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Reviews> getAll() {
        List<Reviews> reviews = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVIEWS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Reviews review = Reviews.builder()
                        .id(resultSet.getInt("id"))
                        .rating(resultSet.getInt("rating"))
                        .comment(resultSet.getString("comment"))
                        .createdAt(resultSet.getObject("created_at", LocalDateTime.class))
                        .build();
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return reviews;
    }

    @Override
    public Optional<Reviews> getReviewsById(int id) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVIEWS_BY_ID_SQL);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
               int rating = resultSet.getInt("rating");
               String comment = resultSet.getString("comment");
               LocalDateTime createdAt = resultSet.getObject("created_at", LocalDateTime.class);
               return Optional.of(Reviews.builder().rating(rating).comment(comment).createdAt(createdAt).build());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
