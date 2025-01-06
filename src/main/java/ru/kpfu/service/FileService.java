package ru.kpfu.service;

import ru.kpfu.dto.fileDTO.FileRequestDTO;
import ru.kpfu.dto.fileDTO.FileResponseDTO;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Optional;

public interface FileService {
    void saveFile(FileRequestDTO fileRequestDTO);
    Optional<FileResponseDTO> getFileByNameAndPath(String name, String path);
    FileResponseDTO saveAndReturnFile(Part filePart) throws IOException;

}
