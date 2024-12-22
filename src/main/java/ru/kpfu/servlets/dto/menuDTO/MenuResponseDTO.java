package ru.kpfu.servlets.dto.menuDTO;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MenuResponseDTO {
    private int id;
    private String name;
    private String description;
    private int price;
    private String imagePath;
}

