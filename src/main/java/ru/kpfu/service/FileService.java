package ru.kpfu.service;

import ru.kpfu.dto.fileDTO.FileRequestDTO;
import ru.kpfu.dto.fileDTO.FileResponseDTO;

import java.util.Optional;

public interface FileService {
    void saveFile(FileRequestDTO fileRequestDTO);
    Optional<FileResponseDTO> getFileByNameAndPath(String name, String path);

}
