package ru.kpfu.servlets.models;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private int id;
    private String name;
    private String description;
    private int price;
    private Integer fileId; // ID файла
    private String imagePath; // Путь к изображению
}

