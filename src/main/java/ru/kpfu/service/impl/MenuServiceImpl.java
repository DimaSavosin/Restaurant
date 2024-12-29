package ru.kpfu.service.impl;

import ru.kpfu.Repositories.MenuDAO;
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
        Menu menu = mapToEntity(menuRequestDTO);
        menuDAO.save(menu);
        return true;
    }

    public List<MenuResponseDTO> getAllMenuWithImages() {
        return menuDAO.getAllWithImages().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public void deleteDish(int id) {
        menuDAO.deleteDishById(id);
    }


    private Menu mapToEntity(MenuRequestDTO menuRequestDTO) {
        return Menu.builder()
                .name(menuRequestDTO.getName())
                .description(menuRequestDTO.getDescription())
                .price(menuRequestDTO.getPrice())
                .fileId(menuRequestDTO.getFileId())
                .imagePath(menuRequestDTO.getImagePath())
                .build();
    }

    private MenuResponseDTO mapToResponseDTO(Menu menu) {
        return MenuResponseDTO.builder()
                .id(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .imagePath(menu.getImagePath())
                .build();
    }
}