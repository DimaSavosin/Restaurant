package ru.kpfu.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Table {
    private int id;
    private int tableNumber;
    private int seatingCapacity;
    private String location;



}
