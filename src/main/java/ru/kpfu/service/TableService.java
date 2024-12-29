package ru.kpfu.service;

import ru.kpfu.dto.tableDTO.TableRequestDTO;
import ru.kpfu.dto.tableDTO.TableResponseDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TableService {
    boolean addTable(TableRequestDTO tableRequestDTO);
    List<TableResponseDTO> getAvailableTables(String location, LocalDate bookingDate, LocalTime bookingTime, int durationHours);
    Optional<TableResponseDTO> getTableById(int id);

}
