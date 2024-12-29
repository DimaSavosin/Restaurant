package ru.kpfu.Repositories;

import ru.kpfu.models.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TableDAO extends CrudDAO<Table> {
    List<Table> getAvailableTables(String location, LocalDate bookingDate, LocalTime bookingTime, int durationHours);
    Optional<Table> getTableById(int tableId);
    boolean isTableNumberExists(int tableNumber);
}
