package ru.kpfu.servlets.models;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Tables {
    private int id;
    private int tableNumber;
    private int seatingCapacity;
    private String location;



}
