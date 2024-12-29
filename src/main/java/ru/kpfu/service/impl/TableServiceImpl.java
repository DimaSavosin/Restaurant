package ru.kpfu.service.impl;

import ru.kpfu.dto.tableDTO.TableResponseDTO;
import ru.kpfu.service.TableService;
import ru.kpfu.Repositories.TableDAO;
import ru.kpfu.dto.tableDTO.TableRequestDTO;
import ru.kpfu.models.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TableServiceImpl implements TableService {
    private final TableDAO tableDAO;

    public TableServiceImpl(TableDAO tableDAO) {
        this.tableDAO = tableDAO;
    }

    // Добавление стола
    public boolean addTable(TableRequestDTO tableRequestDTO) {
        if (tableDAO.isTableNumberExists(tableRequestDTO.getTableNumber())) {
            return false;
        }
        Table table = mapToEntity(tableRequestDTO);
        tableDAO.save(table);

        return true;
    }

    // Получение доступных столов
    public List<TableResponseDTO> getAvailableTables(String location, LocalDate bookingDate, LocalTime bookingTime, int durationHours) {
        return tableDAO.getAvailableTables(location, bookingDate, bookingTime, durationHours)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Получение стола по ID
    public Optional<TableResponseDTO> getTableById(int id) {
        Optional<Table> tableOptional = tableDAO.getTableById(id);
        return tableOptional.map(this::mapToResponseDTO); // Преобразуем в DTO, если таблица найдена
    }


    // Маппинг DTO -> Entity
    private Table mapToEntity(TableRequestDTO dto) {
        return Table.builder()
                .tableNumber(dto.getTableNumber())
                .seatingCapacity(dto.getSeatingCapacity())
                .location(dto.getLocation())
                .build();
    }

    // Маппинг Entity -> ResponseDTO
    private TableResponseDTO mapToResponseDTO(Table table) {
        return TableResponseDTO.builder()
                .id(table.getId())
                .tableNumber(table.getTableNumber())
                .seatingCapacity(table.getSeatingCapacity())
                .location(table.getLocation())
                .build();
    }
}
