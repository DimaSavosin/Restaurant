package ru.kpfu.Repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.Repositories.TableDAO;
import ru.kpfu.models.Table;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class TableDAOImpl implements TableDAO {
    private final DataSource dataSource;
    private static final String INSERT_TABLE_SQL = "INSERT INTO tables (table_number, seating_capacity, location) VALUES (?, ?, ?)";
    private static final String DELETE_TABLE_SQL = "DELETE FROM tables WHERE id = ?";
    private static final String UPDATE_TABLE_SQL = "UPDATE tables SET table_number = ?, seating_capacity = ?, location = ? WHERE id = ?";
    private static final String SELECT_TABLE_BY_ID_SQL = "SELECT * FROM tables WHERE id = ?";
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

    private static final String VALIDATE_UNIQUE_TABLE_NUMBER_SQL = "SELECT COUNT(*) FROM tables WHERE table_number = ?";
    private static final String SELECT_TABLES_SQL =  "select * from tables";
@Override
    public List<Table> getAll(){
        List<Table> tables = new ArrayList();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TABLES_SQL);
            ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int tableNumber = resultSet.getInt("table_number");
                int seatingCapacity = resultSet.getInt("seating_capacity");
                String location = resultSet.getString("location");
                tables.add(Table.builder()
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
    public void save(Table table) {
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
    public void delete(Table table) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TABLE_SQL)) {
            preparedStatement.setInt(1, table.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Table table) {
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
    public List<Table> getAvailableTables(String location, LocalDate bookingDate, LocalTime bookingTime, int durationHours) {

        List<Table> availableTables = new ArrayList<>();
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
                        Table.builder()
                                .id(resultSet.getInt("id"))
                                .tableNumber(resultSet.getInt("table_number"))
                                .seatingCapacity(resultSet.getInt("seating_capacity"))
                                .location(resultSet.getString("location"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return availableTables;
    }

    @Override
    public Optional<Table> getTableById(int tableId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TABLE_BY_ID_SQL)) {

            preparedStatement.setInt(1, tableId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Table table = Table.builder()
                        .id(resultSet.getInt("id"))
                        .tableNumber(resultSet.getInt("table_number"))
                        .seatingCapacity(resultSet.getInt("seating_capacity"))
                        .location(resultSet.getString("location"))
                        .build();
                return Optional.of(table);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
@Override
    public boolean isTableNumberExists(int tableNumber) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(VALIDATE_UNIQUE_TABLE_NUMBER_SQL)) {
            preparedStatement.setInt(1, tableNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


}
