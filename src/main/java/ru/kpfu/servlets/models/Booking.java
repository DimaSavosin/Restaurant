package ru.kpfu.servlets.models;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Booking {
    private int id;
    private int user_id;
    private int tableId;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private int duration; // Продолжительность в часах
    private String status;
}
