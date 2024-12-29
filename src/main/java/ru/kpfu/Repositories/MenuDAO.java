package ru.kpfu.Repositories;

import ru.kpfu.models.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuDAO extends CrudDAO<Menu> {
    Optional<Menu> getMenuById(int id);
    void updateFileId(int menuId, int fileId);
    List<Menu> getAllWithImages();
    void deleteDishById(int id);
    boolean isDishNameExists(String name);


}
