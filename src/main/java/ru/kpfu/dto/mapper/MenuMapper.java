package ru.kpfu.dto.mapper;

import ru.kpfu.dto.menuDTO.MenuRequestDTO;
import ru.kpfu.dto.menuDTO.MenuResponseDTO;
import ru.kpfu.models.Menu;

public class MenuMapper {

    public static Menu toEntity(MenuRequestDTO menuRequestDTO) {
        return Menu.builder()
                .name(menuRequestDTO.getName())
                .description(menuRequestDTO.getDescription())
                .price(menuRequestDTO.getPrice())
                .fileId(menuRequestDTO.getFileId())
                .imagePath(menuRequestDTO.getImagePath())
                .build();
    }

    public static MenuResponseDTO toResponseDTO(Menu menu) {
        return MenuResponseDTO.builder()
                .id(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .imagePath(menu.getImagePath())
                .build();
    }
}
