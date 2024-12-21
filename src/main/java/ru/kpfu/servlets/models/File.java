package ru.kpfu.servlets.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private int id;
    private String name; // Имя файла
    private String path; // Путь к файлу
}
