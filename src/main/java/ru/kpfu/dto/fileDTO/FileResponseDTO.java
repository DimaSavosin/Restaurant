package ru.kpfu.dto.fileDTO;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class FileResponseDTO {
    private int id;
    private String name;
    private String path;
}
