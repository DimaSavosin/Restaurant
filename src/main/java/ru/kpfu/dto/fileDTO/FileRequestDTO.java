package ru.kpfu.dto.fileDTO;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class FileRequestDTO {
    private String name; // Имя файла
    private String path; // Путь к файлу
}
