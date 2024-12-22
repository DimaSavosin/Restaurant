package ru.kpfu.servlets.service;

import ru.kpfu.servlets.Repositories.TableDAO;
import ru.kpfu.servlets.dto.tableDTO.TableRequestDTO;
import ru.kpfu.servlets.dto.tableDTO.TableResponseDTO;
import ru.kpfu.servlets.models.Tables;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

public class TableService {
    private final TableDAO tableDAO;

    public TableService(TableDAO tableDAO) {
        this.tableDAO = tableDAO;
    }

    // Добавление стола
    public void addTable(TableRequestDTO tableRequestDTO) {
        Tables table = mapToEntity(tableRequestDTO);
        tableDAO.save(table);
    }

    // Получение доступных столов
    public List<TableResponseDTO> getAvailableTables(String location, LocalDate bookingDate, LocalTime bookingTime, int durationHours) {
        return tableDAO.getAvailableTables(location, bookingDate, bookingTime, durationHours)
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    // Получение стола по ID
    public TableResponseDTO getTableById(int id) {
        Tables table = tableDAO.getTableById(id);
        return mapToResponseDTO(table);
    }

    // Маппинг DTO -> Entity
    private Tables mapToEntity(TableRequestDTO dto) {
        return Tables.builder()
                .tableNumber(dto.getTableNumber())
                .seatingCapacity(dto.getSeatingCapacity())
                .location(dto.getLocation())
                .build();
    }

    // Маппинг Entity -> ResponseDTO
    private TableResponseDTO mapToResponseDTO(Tables table) {
        return TableResponseDTO.builder()
                .id(table.getId())
                .tableNumber(table.getTableNumber())
                .seatingCapacity(table.getSeatingCapacity())
                .location(table.getLocation())
                .build();
    }
}
