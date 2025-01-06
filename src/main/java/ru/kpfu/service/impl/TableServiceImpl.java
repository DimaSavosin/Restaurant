package ru.kpfu.service.impl;

import ru.kpfu.dto.mapper.TableMapper;
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


    public boolean addTable(TableRequestDTO tableRequestDTO) {
        if (tableDAO.isTableNumberExists(tableRequestDTO.getTableNumber())) {
            return false;
        }
        Table table = TableMapper.toEntity(tableRequestDTO);
        tableDAO.save(table);

        return true;
    }


    public List<TableResponseDTO> getAvailableTables(String location, LocalDate bookingDate, LocalTime bookingTime, int durationHours) {
        return tableDAO.getAvailableTables(location, bookingDate, bookingTime, durationHours)
                .stream()
                .map(TableMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    public Optional<TableResponseDTO> getTableById(int id) {
        Optional<Table> tableOptional = tableDAO.getTableById(id);
        return tableOptional.map(TableMapper::toResponseDTO);
    }
}
