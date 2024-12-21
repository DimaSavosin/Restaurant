package ru.kpfu.servlets.Repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.servlets.Repositories.FileDAO;
import ru.kpfu.servlets.models.File;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class FileDAOImpl implements FileDAO {
    private final DataSource dataSource;

    private static final String INSERT_FILE_SQL = "INSERT INTO files (file_name, file_path) VALUES (?, ?)";
    private static final String SELECT_FILE_BY_ID_SQL = "SELECT * FROM files WHERE id = ?";
    private static final String SELECT_ALL_FILES_SQL = "SELECT * FROM files";
    private static final String SELECT_FILE_BY_NAME_AND_PATH_SQL = "SELECT * FROM files WHERE file_name = ? AND file_path = ?";

    @Override
    public void save(File file) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FILE_SQL)) {
            preparedStatement.setString(1, file.getName()); // file_name
            preparedStatement.setString(2, file.getPath()); // file_path
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(File file) {
        String deleteSQL = "DELETE FROM files WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setInt(1, file.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(File file) {
        String updateSQL = "UPDATE files SET file_name = ?, file_path = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, file.getName()); // file_name
            preparedStatement.setString(2, file.getPath()); // file_path
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
                            .name(resultSet.getString("file_name")) // file_name
                            .path(resultSet.getString("file_path")) // file_path
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
                        .name(resultSet.getString("file_name")) // file_name
                        .path(resultSet.getString("file_path")) // file_path
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
                            .name(resultSet.getString("file_name")) // file_name
                            .path(resultSet.getString("file_path")) // file_path
                            .build());
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
