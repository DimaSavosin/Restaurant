package ru.kpfu.service;

import ru.kpfu.dto.tableDTO.TableResponseDTO;

import java.util.List;

public interface FavoriteTableService {
    void addFavoriteTable(int userId, int tableId);
    void removeFavoriteTable(int userId, int tableId);
    List<Integer> getFavoriteTablesID(int userId);
    List<TableResponseDTO> getFavoriteTablesWithDetails(int userId);



}
