package ru.kpfu.Repositories;

import java.util.List;

public interface FavoriteTableDAO {
    void addFavoriteTable(int userId,int tableId);
    void removeFavoriteTable(int userId,int tableId);
    List<Integer> getFavoriteTablesByUserId(int userId);
    boolean isFavorite(int userId, int tableId);
}
