package ru.kpfu.dto.mapper;

import ru.kpfu.dto.bookingDTO.BookingRequestDTO;
import ru.kpfu.dto.bookingDTO.BookingResponseDTO;
import ru.kpfu.models.Booking;

public class BookingMapper {

    public static Booking toEntity(BookingRequestDTO bookingRequestDTO) {
        return Booking.builder()
                .userId(bookingRequestDTO.getUserId())
                .tableId(bookingRequestDTO.getTableId())
                .bookingDate(bookingRequestDTO.getBookingDate())
                .bookingTime(bookingRequestDTO.getBookingTime())
                .duration(bookingRequestDTO.getDuration())
                .status(bookingRequestDTO.getStatus())
                .build();
    }

    public static BookingResponseDTO toResponseDTO(Booking booking) {
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

