package ru.kpfu.service.impl;

import ru.kpfu.Repositories.FileDAO;
import ru.kpfu.dto.fileDTO.FileRequestDTO;
import ru.kpfu.dto.fileDTO.FileResponseDTO;
import ru.kpfu.dto.mapper.FileMapper;
import ru.kpfu.models.File;
import ru.kpfu.service.FileService;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

public class FileServiceImpl implements FileService {
    private final FileDAO fileDAO;
    private static final String UPLOAD_DIR = "C:/uploads/";

    public FileServiceImpl(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    @Override
    public void saveFile(FileRequestDTO fileRequestDTO) {
        File file = FileMapper.toEntity(fileRequestDTO);
        fileDAO.save(file);
    }

    @Override
    public Optional<FileResponseDTO> getFileByNameAndPath(String name, String path) {
        return fileDAO.getFileByNameAndPath(name, path)
                .map(FileMapper::toResponseDTO);
    }

    public FileResponseDTO saveAndReturnFile(Part filePart) throws IOException {
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uniqueFileName = UUID.randomUUID() + "_" + fileName;

        String filePath = UPLOAD_DIR + uniqueFileName;
        filePart.write(filePath);

        FileRequestDTO fileRequestDTO = FileRequestDTO.builder()
                .name(fileName)
                .path("/ORIS_war_exploded" + "/uploads/" + uniqueFileName)
                .build();

        saveFile(fileRequestDTO);

        return getFileByNameAndPath(fileRequestDTO.getName(), fileRequestDTO.getPath())
                .orElseThrow(() -> new RuntimeException("Ошибка при сохранении файла"));
    }
}
