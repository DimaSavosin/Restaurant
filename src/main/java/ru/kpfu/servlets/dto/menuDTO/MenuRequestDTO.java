package ru.kpfu.servlets.dto.menuDTO;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MenuRequestDTO {
    private String name;
    private String description;
    private int price;
    private Integer fileId;
    private String imagePath;
}
