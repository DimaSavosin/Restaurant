package ru.kpfu.servlets.Repositories;

import ru.kpfu.servlets.models.Booking;


import java.util.List;
import java.util.Optional;

public interface BookingDAO extends CrudDAO<Booking> {
    Optional<Booking> getBookingById(int id);
    void cancelBooking(int bookingId);
    List<Booking> getActiveBookingsByUserId(int userId);
    void updateBookingStatus(int bookingId, String newStatus);
    List<Booking> getAllWithUserNames();
    List<Booking> getActiveBookings();
    List<Booking> getHistoricalBookings();
    int saveAndReturnId(Booking booking);

}
