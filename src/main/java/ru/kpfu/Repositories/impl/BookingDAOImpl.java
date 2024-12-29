package ru.kpfu.Repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.Repositories.BookingDAO;
import ru.kpfu.models.Booking;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BookingDAOImpl implements BookingDAO {
    private final DataSource dataSource;

    private static final String INSERT_BOOKING_SQL = "INSERT INTO bookings (user_id, table_id, booking_date, booking_time, duration, status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_BOOKING_SQL = "delete from bookings where id =?";
    private static final String UPDATE_BOOKING_SQL = "update bookings set user_id = ?, table_id = ?, booking_date = ?, booking_time = ?, status = ? where id = ? ";
    private static final String SELECT_BOOKING_SQL = "select * from bookings";
    private static final String SELECT_BOOKING_BY_ID_SQL = "select * from bookings where id = ?";
    private static final String UPDATE_STATUS_SQL = "update bookings set status = 'cancelled' where id = ?";
    private static final String SELECT_BOOKING_BY_USER_ID_SQL = "SELECT * FROM bookings WHERE user_id = ? AND status IN ('confirmed', 'pending') AND status != 'cancelled' AND (booking_date + booking_time + (duration * INTERVAL '1 hour')) > CURRENT_TIMESTAMP AT TIME ZONE 'Europe/Moscow' ";
    private static final String UPDATE_BOOKING_STATUS_SQL = "update bookings set status = ? where id = ?";
    private static final String SELECT_BOOKING_WITH_USERNAME_SQL = "SELECT b.*, u.name AS user_name FROM bookings b JOIN users u ON b.user_id = u.id";
    private static final String SELECT_ACTIVE_BOOKING_WITH_USERNAME_SQL = """
            SELECT b.*, u.name AS user_name\s
            FROM bookings b
            JOIN users u ON b.user_id = u.id
            WHERE b.status IN ('confirmed', 'pending')\s
              AND (b.booking_date + b.booking_time + (b.duration * INTERVAL '1 hour')) > CURRENT_TIMESTAMP AT TIME ZONE 'Europe/Moscow' ;
            """;
    private static final String SELECT_HISTORICAL_BOOKING_WITH_USERNAME_SQL = """
            SELECT b.*, u.name AS user_name
            FROM bookings b
            JOIN users u ON b.user_id = u.id
            WHERE b.status IN ('confirmed', 'cancelled')
              AND (
                    b.status = 'cancelled'\s
                    OR (b.booking_date + b.booking_time + (b.duration * INTERVAL '1 hour')) <= CURRENT_TIMESTAMP AT TIME ZONE 'Europe/Moscow'
                  );
            """;
    private static final String INSERT_AND_RETURN_ID_SQL = "INSERT INTO bookings (user_id, status) VALUES (?, ?) RETURNING id";



    @Override
    public void save(Booking booking) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOKING_SQL)) {
            preparedStatement.setInt(1, booking.getUser_id());
            preparedStatement.setInt(2, booking.getTableId());
            preparedStatement.setObject(3, booking.getBookingDate());
            preparedStatement.setObject(4, booking.getBookingTime());
            preparedStatement.setInt(5, booking.getDuration());
            preparedStatement.setString(6, booking.getStatus());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Booking booking) {
        try(Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOKING_SQL);
            preparedStatement.setInt(1,booking.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Booking booking) {
        try(Connection connection =dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKING_SQL);
            preparedStatement.setInt(1,booking.getUser_id());
            preparedStatement.setInt(2,booking.getTableId());
            preparedStatement.setObject(3,booking.getBookingDate());
            preparedStatement.setObject(4,booking.getBookingTime());
            preparedStatement.setString(5,booking.getStatus());
            preparedStatement.setInt(6,booking.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Booking> getAll() {
        List<Booking> bookings = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKING_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                bookings.add(Booking.builder()
                        .id(resultSet.getInt("id"))
                        .user_id(resultSet.getInt("user_id"))
                        .tableId(resultSet.getInt("table_id"))
                        .bookingDate(resultSet.getObject("booking_date", LocalDate.class))
                        .bookingTime(resultSet.getObject("booking_time", LocalTime.class))
                        .duration(resultSet.getInt("duration"))
                        .status(resultSet.getString("status"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookings;
    }





    @Override
    public void cancelBooking(int bookingId) {
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS_SQL)) {
            preparedStatement.setInt(1,bookingId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Booking> getActiveBookingsByUserId(int userId) {
        List<Booking> bookings = new ArrayList<>();
        try (Connection connection  = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOKING_BY_USER_ID_SQL)) {
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                bookings.add(Booking.builder()
                        .id(resultSet.getInt("id"))
                        .user_id(resultSet.getInt("user_id"))
                        .tableId(resultSet.getInt("table_id"))
                        .bookingDate(resultSet.getObject("booking_date", LocalDate.class))
                        .bookingTime(resultSet.getObject("booking_time", LocalTime.class))
                        .duration(resultSet.getInt("duration"))
                        .status(resultSet.getString("status"))
                        .build());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bookings;

    }

    @Override
    public void updateBookingStatus(int bookingId, String newStatus) {
        try(Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOKING_STATUS_SQL)) {
            preparedStatement.setString(1,newStatus);
            preparedStatement.setInt(2,bookingId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Booking> getActiveBookings() {
        List<Booking> bookings = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACTIVE_BOOKING_WITH_USERNAME_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookings.add(Booking.builder()
                        .id(resultSet.getInt("id"))
                        .user_id(resultSet.getInt("user_id"))
                        .tableId(resultSet.getInt("table_id"))
                        .bookingDate(resultSet.getObject("booking_date", LocalDate.class))
                        .bookingTime(resultSet.getObject("booking_time", LocalTime.class))
                        .duration(resultSet.getInt("duration"))
                        .status(resultSet.getString("status"))
                        .userName(resultSet.getString("user_name"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookings;
    }

    @Override
    public List<Booking> getHistoricalBookings() {
        List<Booking> bookings = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HISTORICAL_BOOKING_WITH_USERNAME_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bookings.add(Booking.builder()
                        .id(resultSet.getInt("id"))
                        .user_id(resultSet.getInt("user_id"))
                        .tableId(resultSet.getInt("table_id"))
                        .bookingDate(resultSet.getObject("booking_date", LocalDate.class))
                        .bookingTime(resultSet.getObject("booking_time", LocalTime.class))
                        .duration(resultSet.getInt("duration"))
                        .status(resultSet.getString("status"))
                        .userName(resultSet.getString("user_name"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookings;
    }




}
