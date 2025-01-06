package ru.kpfu.Repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.Repositories.ReviewDAO;
import ru.kpfu.models.Review;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ReviewDAOImpl implements ReviewDAO {
    private final DataSource dataSource;

    private static final String INSERT_REVIEW_SQL = "INSERT INTO reviews (user_id, rating, comment) VALUES (?, ?, ?)";
    private static final String DELETE_REVIEW_SQL = "DELETE FROM reviews WHERE id = ?";
    private static final String UPDATE_REVIEW_SQL = "UPDATE reviews SET user_id = ?, rating = ?, comment = ?, created_at = ? WHERE id = ?";
    private static final String SELECT_REVIEWS_SQL = "SELECT * FROM reviews ORDER BY created_at DESC";
    private static final String SELECT_REVIEWS_WITH_USER_NAME_SQL = "SELECT r.id, r.rating, r.comment, r.created_at, r.user_id, u.name AS user_name " +
            "FROM reviews r " +
            "JOIN users u ON r.user_id = u.id " +
            "ORDER BY r.created_at DESC";
    private static final String SELECT_AVG_RATING_SQL = "SELECT AVG(rating) FROM reviews";
    private static final String DELETE_REVIEW_BY_ID = "DELETE FROM reviews WHERE id = ?";
    @Override
    public void save(Review review) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REVIEW_SQL)) {

            preparedStatement.setInt(1, review.getUserId());
            preparedStatement.setInt(2, review.getRating());
            preparedStatement.setString(3, review.getComment());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Review review) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REVIEW_SQL)) {

            preparedStatement.setInt(1, review.getUserId());
            preparedStatement.setInt(2, review.getRating());
            preparedStatement.setString(3, review.getComment());
            preparedStatement.setObject(4, review.getCreatedAt());
            preparedStatement.setInt(5, review.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Review review) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REVIEW_SQL)) {

            preparedStatement.setInt(1, review.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Review> getAll() {
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVIEWS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Review review = Review.builder()
                        .id(resultSet.getInt("id"))
                        .userId(resultSet.getInt("user_id"))
                        .rating(resultSet.getInt("rating"))
                        .comment(resultSet.getString("comment"))
                        .createdAt(resultSet.getObject("created_at", Timestamp.class))
                        .build();
                reviews.add(review);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviews;
    }


    @Override
    public List<Review> getReviewsWithUserName() {
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_REVIEWS_WITH_USER_NAME_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Review review = Review.builder()
                        .id(resultSet.getInt("id"))
                        .userId(resultSet.getInt("user_id"))
                        .userName(resultSet.getString("user_name"))
                        .rating(resultSet.getInt("rating"))
                        .comment(resultSet.getString("comment"))
                        .createdAt(resultSet.getTimestamp("created_at"))
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
    public double getAverageRating() {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AVG_RATING_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0.0;
    }

    @Override
    public void deleteReviewById(int id) {
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REVIEW_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
