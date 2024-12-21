package ru.kpfu.servlets.service;

import ru.kpfu.servlets.Repositories.FileDAO;
import ru.kpfu.servlets.models.File;

import java.util.Optional;

public class FileService {
    private final FileDAO fileDAO;

    public FileService(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public void saveFile(File file) {
        fileDAO.save(file);
    }


    public Optional<File> getFileById(int id) {
        return fileDAO.getFileById(id);
    }

    public Optional<File> getFileByNameAndPath(String name, String path) {
        return fileDAO.getFileByNameAndPath(name, path);
    }

}
