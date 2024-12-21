package ru.kpfu.servlets.service;

import ru.kpfu.servlets.Repositories.MenuDAO;
import ru.kpfu.servlets.models.Menu;

import java.util.List;

public class MenuService {
    private final MenuDAO menuDAO;

    public MenuService(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    public void addMenu(Menu menu) {
        menuDAO.save(menu);
    }

    public List<Menu> getAllMenu() {
        return menuDAO.getAll();
    }

    public void updateMenuFile(int menuId, int fileId) {
        menuDAO.updateFileId(menuId, fileId);
    }

}