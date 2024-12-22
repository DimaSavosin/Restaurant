
package ru.kpfu.servlets.dto.bookingDTO;

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
public class BookingRequestDTO {
    private int userId;
    private int tableId;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private int duration;
    private String status;
}