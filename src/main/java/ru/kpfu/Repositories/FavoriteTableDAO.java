package ru.kpfu.Repositories;


import ru.kpfu.models.Table;

import java.util.List;

public interface FavoriteTableDAO {
    void addFavoriteTable(int userId,int tableId);
    void removeFavoriteTable(int userId,int tableId);
    List<Integer> getFavoriteTablesIdByUserId(int userId);
    List<Table> getFavoriteTableDetails(int userId);
}
