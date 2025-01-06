package ru.kpfu.dto.fileDTO;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class FileRequestDTO {
    private String name;
    private String path;
}
