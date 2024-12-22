package ru.kpfu.servlets.service;

import ru.kpfu.servlets.Repositories.MenuDAO;

import ru.kpfu.servlets.dto.menuDTO.MenuRequestDTO;
import ru.kpfu.servlets.dto.menuDTO.MenuResponseDTO;
import ru.kpfu.servlets.models.Menu;

import java.util.List;
import java.util.stream.Collectors;

public class MenuService {
    private final MenuDAO menuDAO;

    public MenuService(MenuDAO menuDAO) {
        this.menuDAO = menuDAO;
    }

    public void addMenu(MenuRequestDTO menuRequestDTO) {
        Menu menu = mapToEntity(menuRequestDTO);
        menuDAO.save(menu);
    }

    public List<MenuResponseDTO> getAllMenuWithImages() {
        return menuDAO.getAllWithImages().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
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