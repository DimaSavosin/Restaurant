package ru.kpfu.service.impl;

import ru.kpfu.dto.mapper.BookingMapper;
import ru.kpfu.service.BookingService;
import ru.kpfu.Repositories.BookingDAO;
import ru.kpfu.dto.bookingDTO.BookingRequestDTO;
import ru.kpfu.dto.bookingDTO.BookingResponseDTO;
import ru.kpfu.models.Booking;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {
    BookingDAO bookingDAO;
    private static final LocalTime RESTAURANT_OPEN_TIME = LocalTime.of(10, 0);
    private static final LocalTime RESTAURANT_CLOSE_TIME = LocalTime.of(22, 0);

    public BookingServiceImpl(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public void addBooking(BookingRequestDTO bookingRequestDTO) {
        Booking booking = BookingMapper.toEntity(bookingRequestDTO);
        bookingDAO.save(booking);
    }

    public List<BookingResponseDTO> getActiveBookingsByUserId(int userId) {
        return bookingDAO.getActiveBookingsByUserId(userId).stream()
                .map(BookingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<BookingResponseDTO> getActiveBookings() {
        return bookingDAO.getActiveBookings().stream()
                .map(BookingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<BookingResponseDTO> getHistoricalBookings() {
        return bookingDAO.getHistoricalBookings().stream()
                .map(BookingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void updateBookingStatus(int bookingId, String newStatus) {
        bookingDAO.updateBookingStatus(bookingId, newStatus);
    }

    public void cancelBooking(int bookingId) {
        bookingDAO.cancelBooking(bookingId);
    }

    public boolean isBookingTimeValid(LocalTime bookingTime, int duration) {
        LocalTime bookingEndTime = bookingTime.plusHours(duration);
        return !bookingTime.isBefore(RESTAURANT_OPEN_TIME) && !bookingEndTime.isAfter(RESTAURANT_CLOSE_TIME);
    }
}
