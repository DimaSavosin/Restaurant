package ru.kpfu.service;

import java.util.List;

public interface FavoriteTableService {
    void addFavoriteTable(int userId, int tableId);
    void removeFavoriteTable(int userId, int tableId);
    List<Integer> getFavoriteTables(int userId);


}
