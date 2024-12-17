package ru.kpfu.servlets.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private int id;
    private String name;
    private int price;
    private String description;
}
