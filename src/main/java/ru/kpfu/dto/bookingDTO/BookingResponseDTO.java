package ru.kpfu.dto.bookingDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO {
    private int id;
    private String userName;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private int duration;
    private String status;
    private int tableId;

}