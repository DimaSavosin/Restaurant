package ru.kpfu.service;

import ru.kpfu.dto.menuDTO.MenuRequestDTO;
import ru.kpfu.dto.menuDTO.MenuResponseDTO;

import java.util.List;

public interface MenuService {
    boolean addMenu(MenuRequestDTO menuRequestDTO);
    List<MenuResponseDTO> getAllMenuWithImages();
    void deleteDish(int id);
}
