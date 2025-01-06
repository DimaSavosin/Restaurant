package ru.kpfu.models;
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
    private int fileId;
    private String imagePath;
}

