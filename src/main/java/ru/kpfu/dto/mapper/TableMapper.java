package ru.kpfu.dto.mapper;



import ru.kpfu.dto.tableDTO.TableRequestDTO;
import ru.kpfu.dto.tableDTO.TableResponseDTO;
import ru.kpfu.models.Table;

public class TableMapper {

    public static Table toEntity(TableRequestDTO dto) {
        return Table.builder()
                .tableNumber(dto.getTableNumber())
                .seatingCapacity(dto.getSeatingCapacity())
                .location(dto.getLocation())
                .build();
    }

    public static TableResponseDTO toResponseDTO(Table table) {
        return TableResponseDTO.builder()
                .id(table.getId())
                .tableNumber(table.getTableNumber())
                .seatingCapacity(table.getSeatingCapacity())
                .location(table.getLocation())
                .build();
    }
}

