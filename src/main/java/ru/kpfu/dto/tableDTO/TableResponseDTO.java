package ru.kpfu.dto.tableDTO;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TableResponseDTO {
    private int id;
    private int tableNumber;
    private int seatingCapacity;
    private String location;
}