package ru.kpfu.dto.mapper;



import ru.kpfu.dto.fileDTO.FileRequestDTO;
import ru.kpfu.dto.fileDTO.FileResponseDTO;
import ru.kpfu.models.File;

public class FileMapper {

    public static File toEntity(FileRequestDTO fileRequestDTO) {
        return File.builder()
                .name(fileRequestDTO.getName())
                .path(fileRequestDTO.getPath())
                .build();
    }

    public static FileResponseDTO toResponseDTO(File file) {
        return FileResponseDTO.builder()
                .id(file.getId())
                .name(file.getName())
                .path(file.getPath())
                .build();
    }
}

