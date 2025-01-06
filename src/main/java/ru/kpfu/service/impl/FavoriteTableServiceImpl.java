package ru.kpfu.service.impl;

import ru.kpfu.Repositories.FavoriteTableDAO;
import ru.kpfu.dto.mapper.TableMapper;
import ru.kpfu.dto.tableDTO.TableResponseDTO;
import ru.kpfu.service.FavoriteTableService;

import java.util.List;
import java.util.stream.Collectors;

public class FavoriteTableServiceImpl implements FavoriteTableService {
    private final FavoriteTableDAO favoriteTableDAO;

    public FavoriteTableServiceImpl(FavoriteTableDAO favoriteTableDAO) {
        this.favoriteTableDAO = favoriteTableDAO;
    }

    public void addFavoriteTable(int userId, int tableId) {
        favoriteTableDAO.addFavoriteTable(userId, tableId);
    }

    public void removeFavoriteTable(int userId, int tableId) {
        favoriteTableDAO.removeFavoriteTable(userId, tableId);
    }

    public List<Integer> getFavoriteTablesID(int userId) {
        return favoriteTableDAO.getFavoriteTablesIdByUserId(userId);
    }

    @Override
    public List<TableResponseDTO> getFavoriteTablesWithDetails(int userId) {
        return favoriteTableDAO.getFavoriteTableDetails(userId).stream()
                .map(TableMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
