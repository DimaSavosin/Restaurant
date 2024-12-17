package ru.kpfu.servlets.service;

import ru.kpfu.servlets.Repositories.TableDAO;
import ru.kpfu.servlets.models.Tables;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TableService {
    private TableDAO tableDAO;
    public TableService(TableDAO tableDAO) {
        this.tableDAO = tableDAO;
    }
    public List<Tables> getAllTables() {
        return tableDAO.getAll();
    }
    public boolean checkTable(int tableId, LocalDate bookingDate, LocalTime bookingTime){
        return tableDAO.isTableAvailable(tableId, bookingDate, bookingTime);
    }

    public List<Tables> getAvailableTables(String location, LocalDate bookingDate, LocalTime bookingTime, int durationHours) {
        return tableDAO.getAvailableTables(location, bookingDate, bookingTime, durationHours);
    }






}
