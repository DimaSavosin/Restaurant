package ru.kpfu.servlets.service;

import ru.kpfu.servlets.Repositories.TableDAO;

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



    public List<Tables> getAvailableTables(String location, LocalDate bookingDate, LocalTime bookingTime, int durationHours) {
        return tableDAO.getAvailableTables(location, bookingDate, bookingTime, durationHours);
    }

    public Tables getTableById(int tableId) {
        return tableDAO.getTableById(tableId);
    }

    public void addTable(Tables table) {
        tableDAO.save(table);
    }
}
