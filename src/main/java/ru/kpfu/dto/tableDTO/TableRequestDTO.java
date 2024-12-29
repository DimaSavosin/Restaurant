package ru.kpfu.dto.tableDTO;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class TableRequestDTO {
    private int tableNumber;
    private int seatingCapacity;
    private String location;
}
