package ru.kpfu.service;

import ru.kpfu.dto.bookingDTO.BookingRequestDTO;
import ru.kpfu.dto.bookingDTO.BookingResponseDTO;

import java.time.LocalTime;
import java.util.List;

public interface BookingService {
    void addBooking(BookingRequestDTO bookingRequestDTO);
    List<BookingResponseDTO> getActiveBookingsByUserId(int userId);
    List<BookingResponseDTO> getActiveBookings();
    List<BookingResponseDTO> getHistoricalBookings();
    void updateBookingStatus(int bookingId, String newStatus);
    void cancelBooking(int bookingId);
    boolean isBookingTimeValid(LocalTime bookingTime, int duration);


}
