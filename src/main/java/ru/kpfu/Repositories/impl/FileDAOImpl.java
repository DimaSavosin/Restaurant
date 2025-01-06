package ru.kpfu.Repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.Repositories.FileDAO;
import ru.kpfu.models.File;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FileDAOImpl implements FileDAO {
    private final DataSource dataSource;
    private static final String DELETE_FILE_SQL = "DELETE FROM files WHERE id = ?";
    private static final String UPDATE_FILE_SQL = "UPDATE files SET file_name = ?, file_path = ? WHERE id = ?";
    private static final String INSERT_FILE_SQL = "INSERT INTO files (file_name, file_path) VALUES (?, ?)";
    private static final String SELECT_FILE_BY_ID_SQL = "SELECT * FROM files WHERE id = ?";
    private static final String SELECT_ALL_FILES_SQL = "SELECT * FROM files";
    private static final String SELECT_FILE_BY_NAME_AND_PATH_SQL = "SELECT * FROM files WHERE file_name = ? AND file_path = ?";

    @Override
    public void save(File file) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILE_SQL)) {
            preparedStatement.setString(1, file.getName());
            preparedStatement.setString(2, file.getPath());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(File file) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FILE_SQL)) {
            preparedStatement.setInt(1, file.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(File file) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FILE_SQL)) {
            preparedStatement.setString(1, file.getName());
            preparedStatement.setString(2, file.getPath());
            preparedStatement.setInt(3, file.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<File> getFileById(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FILE_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(File.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("file_name"))
                            .path(resultSet.getString("file_path"))
                            .build());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<File> getAll() {
        List<File> files = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FILES_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                files.add(File.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("file_name"))
                        .path(resultSet.getString("file_path"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return files;
    }

    @Override
    public Optional<File> getFileByNameAndPath(String name, String path) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FILE_BY_NAME_AND_PATH_SQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, path);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(File.builder()
                            .id(resultSet.getInt("id"))
                            .name(resultSet.getString("file_name"))
                            .path(resultSet.getString("file_path"))
                            .build());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
