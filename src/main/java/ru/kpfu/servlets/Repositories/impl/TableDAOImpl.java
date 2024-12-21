package ru.kpfu.servlets.Repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.servlets.Repositories.TableDAO;
import ru.kpfu.servlets.models.Tables;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
public class TableDAOImpl implements TableDAO {
    private final DataSource dataSource;
    private static final String INSERT_TABLE_SQL = "INSERT INTO tables (table_number, seating_capacity, location) VALUES (?, ?, ?)";
    private static final String DELETE_TABLE_SQL = "DELETE FROM tables WHERE id = ?";
    private static final String UPDATE_TABLE_SQL = "UPDATE tables SET table_number = ?, seating_capacity = ?, location = ? WHERE id = ?";
    private static final String FILTER_TABLE_SQL = """
        SELECT t.* FROM tables t
           WHERE t.location = ?
             AND t.id NOT IN (
                 SELECT b.table_id
                 FROM bookings b
                 WHERE b.booking_date = ?
                   AND b.status IN ('confirmed', 'pending')
                   AND (
                       b.booking_time < (? + INTERVAL '1 HOUR' * ?)
                       AND (? < b.booking_time + (b.duration * INTERVAL '1 HOUR'))
                   )
             )
           
    """;
    private static final String SELECT_TABLES_SQL =  "select * from tables";
@Override
    public List<Tables> getAll(){
        List<Tables> tables = new ArrayList();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TABLES_SQL);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int tableNumber = resultSet.getInt("table_number");
                int seatingCapacity = resultSet.getInt("seating_capacity");
                String location = resultSet.getString("location");
                tables.add(Tables.builder()
                        .id(id)
                        .tableNumber(tableNumber)
                        .seatingCapacity(seatingCapacity)
                        .location(location)
                        .build());
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tables;
    }

    @Override
    public boolean isTableAvailable(int tableId, LocalDate bookingDate, LocalTime bookingTime) {
        String query = "SELECT COUNT(*) FROM bookings WHERE table_id = ? AND booking_date = ? AND booking_time = ? AND status IN ('confirmed', 'pending')";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, tableId);
            preparedStatement.setObject(2, bookingDate);
            preparedStatement.setObject(3, bookingTime);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) == 0; // Возвращаем true, если нет записей
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public void save(Tables table) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TABLE_SQL)) {
            preparedStatement.setInt(1, table.getTableNumber());
            preparedStatement.setInt(2, table.getSeatingCapacity());
            preparedStatement.setString(3, table.getLocation());



            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Tables table) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TABLE_SQL)) {
            preparedStatement.setInt(1, table.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Tables table) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TABLE_SQL)) {
            preparedStatement.setInt(1, table.getTableNumber());
            preparedStatement.setInt(2, table.getSeatingCapacity());
            preparedStatement.setString(3, table.getLocation());
            preparedStatement.setInt(4, table.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Tables> getAvailableTables(String location, LocalDate bookingDate, LocalTime bookingTime, int durationHours) {

        List<Tables> availableTables = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FILTER_TABLE_SQL)) {


            preparedStatement.setString(1, location);
            preparedStatement.setObject(2, bookingDate);
            preparedStatement.setObject(3, bookingTime);
            preparedStatement.setInt(4, durationHours);
            preparedStatement.setObject(5, bookingTime);


            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                availableTables.add(
                        Tables.builder()
                                .id(resultSet.getInt("id"))
                                .tableNumber(resultSet.getInt("table_number"))
                                .seatingCapacity(resultSet.getInt("seating_capacity"))
                                .location(resultSet.getString("location"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении доступных столов", e);
        }
        return availableTables;
    }

    @Override
    public Tables getTableById(int tableId) {
        String sql = "SELECT * FROM tables WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, tableId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Tables.builder()
                        .id(resultSet.getInt("id"))
                        .tableNumber(resultSet.getInt("table_number"))
                        .seatingCapacity(resultSet.getInt("seating_capacity"))
                        .location(resultSet.getString("location"))
                        .build();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch table by ID", e);
        }
        return null; // Если стол с таким ID не найден
    }


}
