package ru.kpfu.servlets.Repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.servlets.Repositories.MenuDAO;
import ru.kpfu.servlets.models.Menu;

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
    private static final String SELECT_MENU_BY_ID_SQL = "SELECT * FROM menu WHERE id = ?";
    private static final String UPDATE_MENU_FILE_SQL = "UPDATE menu SET file_id = ? WHERE id = ?";

    @Override
    public void save(Menu menu) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MENU_SQL)) {

            preparedStatement.setString(1, menu.getName());
            preparedStatement.setString(2, menu.getDescription());
            preparedStatement.setInt(3, menu.getPrice());
            if (menu.getFileId() != null) {
                preparedStatement.setInt(4, menu.getFileId());
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }

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
            if (menu.getFileId() != null) {
                preparedStatement.setInt(4, menu.getFileId());
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }
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
                Integer fileId = resultSet.getObject("file_id", Integer.class);

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
    public Optional<Menu> getMenuById(int id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MENU_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int price = resultSet.getInt("price");
                Integer fileId = resultSet.getObject("file_id", Integer.class);

                return Optional.of(
                        Menu.builder()
                                .id(id)
                                .name(name)
                                .description(description)
                                .price(price)
                                .fileId(fileId)
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void updateFileId(int menuId, int fileId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MENU_FILE_SQL)) {
            preparedStatement.setInt(1, fileId);
            preparedStatement.setInt(2, menuId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
