package ru.kpfu.service.impl;

import ru.kpfu.service.BookingService;
import ru.kpfu.Repositories.BookingDAO;
import ru.kpfu.dto.bookingDTO.BookingRequestDTO;
import ru.kpfu.dto.bookingDTO.BookingResponseDTO;
import ru.kpfu.models.Booking;

import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements BookingService {
    BookingDAO bookingDAO;

    public BookingServiceImpl(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }

    public void addBooking(BookingRequestDTO bookingRequestDTO) {
        Booking booking = mapToEntity(bookingRequestDTO);
        bookingDAO.save(booking);
    }

    public List<BookingResponseDTO> getActiveBookingsByUserId(int userId) {
        return bookingDAO.getActiveBookingsByUserId(userId).stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<BookingResponseDTO> getActiveBookings() {
        return bookingDAO.getActiveBookings().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public List<BookingResponseDTO> getHistoricalBookings() {
        return bookingDAO.getHistoricalBookings().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }
    public void updateBookingStatus(int bookingId, String newStatus) {
        bookingDAO.updateBookingStatus(bookingId, newStatus);
    }

    public void cancelBooking(int bookingId) {
        bookingDAO.cancelBooking(bookingId);
    }




    private Booking mapToEntity(BookingRequestDTO bookingRequestDTO) {
        return Booking.builder()
                .user_id(bookingRequestDTO.getUserId())
                .tableId(bookingRequestDTO.getTableId())
                .bookingDate(bookingRequestDTO.getBookingDate())
                .bookingTime(bookingRequestDTO.getBookingTime())
                .duration(bookingRequestDTO.getDuration())
                .status(bookingRequestDTO.getStatus())
                .build();
    }

    private BookingResponseDTO mapToResponseDTO(Booking booking) {
        return BookingResponseDTO.builder()
                .id(booking.getId())
                .userName(booking.getUserName())
                .bookingDate(booking.getBookingDate())
                .bookingTime(booking.getBookingTime())
                .duration(booking.getDuration())
                .status(booking.getStatus())
                .tableId(booking.getTableId())
                .build();
    }
}