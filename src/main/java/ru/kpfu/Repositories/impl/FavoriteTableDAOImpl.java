package ru.kpfu.Repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.Repositories.FavoriteTableDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FavoriteTableDAOImpl implements FavoriteTableDAO {
    private final DataSource dataSource;

    private static final String INSERT_FAVORITE_SQL = "INSERT INTO user_favorite_tables (user_id, table_id) VALUES (?, ?)";
    private static final String DELETE_FAVORITE_SQL = "DELETE FROM user_favorite_tables WHERE user_id = ? AND table_id = ?";
    private static final String SELECT_FAVORITES_BY_USER_SQL = "SELECT table_id FROM user_favorite_tables WHERE user_id = ?";
    private static final String CHECK_FAVORITE_SQL = "SELECT COUNT(*) FROM user_favorite_tables WHERE user_id = ? AND table_id = ?";
    @Override
    public void addFavoriteTable(int userId, int tableId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FAVORITE_SQL)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, tableId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeFavoriteTable(int userId, int tableId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FAVORITE_SQL)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, tableId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> getFavoriteTablesByUserId(int userId) {
        List<Integer> favoriteTables = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FAVORITES_BY_USER_SQL)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                favoriteTables.add(resultSet.getInt("table_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return favoriteTables;
    }

    @Override
    public boolean isFavorite(int userId, int tableId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CHECK_FAVORITE_SQL)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, tableId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
