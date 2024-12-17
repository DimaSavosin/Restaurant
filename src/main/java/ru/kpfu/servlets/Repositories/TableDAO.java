package ru.kpfu.servlets.Repositories;

import ru.kpfu.servlets.models.Tables;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TableDAO extends CrudDAO<Tables> {
    boolean isTableAvailable(int tableId, LocalDate bookingDate, LocalTime bookingTime);
    List<Tables> getAvailableTables(String location, LocalDate bookingDate, LocalTime bookingTime, int durationHours);

}
