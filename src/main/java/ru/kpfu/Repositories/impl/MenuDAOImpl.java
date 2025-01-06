// MenuDAOImpl.java
package ru.kpfu.Repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.Repositories.MenuDAO;
import ru.kpfu.models.Menu;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MenuDAOImpl implements MenuDAO {
    private final DataSource dataSource;

    private static final String INSERT_MENU_SQL = "INSERT INTO menu (name, description, price, file_id) VALUES (?, ?, ?, ?)";
    private static final String DELETE_MENU_SQL = "DELETE FROM menu WHERE id = ?";
    private static final String UPDATE_MENU_SQL = "UPDATE menu SET name = ?, description = ?, price = ?, file_id = ? WHERE id = ?";
    private static final String SELECT_MENU_SQL = "SELECT id, name, description, price, file_id FROM menu";
    private static final String SELECT_MENU_WITH_IMAGE_SQL = "SELECT m.id, m.name, m.description, m.price, m.file_id, f.file_path AS image_path " +
            "FROM menu m LEFT JOIN files f ON m.file_id = f.id";
    private static final String DELETE_DISH_SQL = "DELETE FROM menu WHERE id = ?";
    private static final String VALIDATE_UNIQUE_DISH_NAME = "SELECT COUNT(*) FROM menu WHERE name = ?";

    @Override
    public void save(Menu menu) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MENU_SQL)) {

            preparedStatement.setString(1, menu.getName());
            preparedStatement.setString(2, menu.getDescription());
            preparedStatement.setInt(3, menu.getPrice());
            preparedStatement.setInt(4, menu.getFileId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Menu menu) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MENU_SQL)) {

            preparedStatement.setInt(1, menu.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Menu menu) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MENU_SQL)) {

            preparedStatement.setString(1, menu.getName());
            preparedStatement.setString(2, menu.getDescription());
            preparedStatement.setInt(3, menu.getPrice());
            preparedStatement.setInt(4, menu.getFileId());
            preparedStatement.setInt(5, menu.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Menu> getAll() {
        List<Menu> menuList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MENU_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                int fileId = resultSet.getInt("file_id");

                menuList.add(Menu.builder()
                        .id(id)
                        .name(name)
                        .description(description)
                        .price(price)
                        .fileId(fileId)
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return menuList;
    }

    @Override
    public List<Menu> getAllWithImages() {
        List<Menu> menuList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MENU_WITH_IMAGE_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                int fileId = resultSet.getInt("file_id");
                String imagePath = resultSet.getString("image_path");

                menuList.add(Menu.builder()
                        .id(id)
                        .name(name)
                        .description(description)
                        .price(price)
                        .fileId(fileId)
                        .imagePath(imagePath)
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return menuList;
    }
    @Override
    public void deleteDishById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DISH_SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении блюда.", e);
        }
    }



    @Override
    public boolean isDishNameExists(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_UNIQUE_DISH_NAME)) {

            preparedStatement.setString(1, name);
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
