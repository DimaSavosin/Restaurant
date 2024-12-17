package ru.kpfu.servlets.Repositories.impl;


import ru.kpfu.servlets.Repositories.UserDAO;
import ru.kpfu.servlets.models.User;
import lombok.RequiredArgsConstructor;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {
    private final DataSource dataSource;

    private static final String INSERT_USER_SQL = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";

    private static final String SELECT_USERS_SQL = "SELECT * FROM users";

    private static final String SELECT_USER_BY_ID_SQL = "SELECT name, email, role FROM users WHERE id = ?";

    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ? ";

    private static final String SELECT_USER_BY_EMAIL_SQL = "SELECT id FROM users WHERE email = ?";
    private static final String SELECT_EMAIL_VALIDITY_SQL = "SELECT COUNT(*) FROM users WHERE email = ?";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER_SQL = "UPDATE users SET name = ?, email = ?, password = ?, role = ? WHERE id = ?";



    @Override
    public void save(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void delete(User user) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole()); // Обновляем роль
            preparedStatement.setInt(5, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role"); // Получаем роль
                users.add(User.builder()
                        .id(id)
                        .name(name)
                        .email(email)
                        .password(password)
                        .role(role)
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }


    @Override
    public User getUserById(int id) {
        User user = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role"); // Получаем роль
                user = User.builder()
                        .id(id)
                        .name(name)
                        .email(email)
                        .role(role)
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override

    public int getUserIdByEmail(String email) throws SQLException {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL_SQL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
         catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return -1;
    }


    @Override
    public User getUserByEmail(String email) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .role(resultSet.getString("role")) // Добавлено поле role
                        .build();
            }
        }
        return null;
    }


    @Override
    public boolean isEmailValid(String email) {

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMAIL_VALIDITY_SQL)) {

            preparedStatement.setString(1, email);
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
