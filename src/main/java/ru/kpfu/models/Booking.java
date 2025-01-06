package ru.kpfu.models;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter

public class Booking {
    private int id;
    private int userId;
    private int tableId;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private int duration;
    private String status;
    private String userName;
}
