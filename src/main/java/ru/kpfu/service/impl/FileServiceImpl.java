package ru.kpfu.service.impl;

import ru.kpfu.Repositories.FileDAO;
import ru.kpfu.dto.fileDTO.FileRequestDTO;
import ru.kpfu.dto.fileDTO.FileResponseDTO;
import ru.kpfu.models.File;
import ru.kpfu.service.FileService;

import java.util.Optional;

public class FileServiceImpl implements FileService {
    private final FileDAO fileDAO;

    public FileServiceImpl(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public void saveFile(FileRequestDTO fileRequestDTO) {
        File file = mapToEntity(fileRequestDTO);
        fileDAO.save(file);
    }

    public Optional<FileResponseDTO> getFileById(int id) {
        return fileDAO.getFileById(id).map(this::mapToResponseDTO);
    }

    public Optional<FileResponseDTO> getFileByNameAndPath(String name, String path) {
        return fileDAO.getFileByNameAndPath(name, path).map(this::mapToResponseDTO);
    }

    private File mapToEntity(FileRequestDTO fileRequestDTO) {
        return File.builder()
                .name(fileRequestDTO.getName())
                .path(fileRequestDTO.getPath())
                .build();
    }

    private FileResponseDTO mapToResponseDTO(File file) {
        return FileResponseDTO.builder()
                .id(file.getId())
                .name(file.getName())
                .path(file.getPath())
                .build();
    }
}