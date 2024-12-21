package ru.kpfu.servlets.Repositories;

import ru.kpfu.servlets.models.File;

import java.util.Optional;

public interface FileDAO extends CrudDAO<File> {
    Optional<File> getFileById(int id);
    Optional<File> getFileByNameAndPath(String name, String path);

}
