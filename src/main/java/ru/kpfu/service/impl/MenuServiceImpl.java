package ru.kpfu.service.impl;

import ru.kpfu.Repositories.MenuDAO;
import ru.kpfu.dto.mapper.MenuMapper;
import ru.kpfu.dto.menuDTO.MenuRequestDTO;
import ru.kpfu.dto.menuDTO.MenuResponseDTO;
import ru.kpfu.models.Menu;
import ru.kpfu.service.MenuService;

import java.util.List;
import java.util.stream.Collectors;

public class MenuServiceImpl implements MenuService {
    private final MenuDAO menuDAO;

    public MenuServiceImpl(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    public boolean addMenu(MenuRequestDTO menuRequestDTO) {
        if (menuDAO.isDishNameExists(menuRequestDTO.getName())) {
            return false;
        }
        Menu menu = MenuMapper.toEntity(menuRequestDTO);
        menuDAO.save(menu);
        return true;
    }

    public List<MenuResponseDTO> getAllMenuWithImages() {
        return menuDAO.getAllWithImages().stream()
                .map(MenuMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteDish(int id) {
        menuDAO.deleteDishById(id);
    }
}
