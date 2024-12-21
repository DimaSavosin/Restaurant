package ru.kpfu.servlets.service;

import ru.kpfu.servlets.Repositories.FavoriteTableDAO;

import java.util.List;

public class FavoriteTableService {
    private final FavoriteTableDAO favoriteTableDAO;

    public FavoriteTableService(FavoriteTableDAO favoriteTableDAO) {
        this.favoriteTableDAO = favoriteTableDAO;
    }

    public void addFavoriteTable(int userId, int tableId) {
        favoriteTableDAO.addFavoriteTable(userId, tableId);
    }

    public void removeFavoriteTable(int userId, int tableId) {
        favoriteTableDAO.removeFavoriteTable(userId, tableId);
    }

    public List<Integer> getFavoriteTables(int userId) {
        return favoriteTableDAO.getFavoriteTablesByUserId(userId);
    }

    public boolean isFavorite(int userId, int tableId) {
        return favoriteTableDAO.isFavorite(userId, tableId);
    }
}
