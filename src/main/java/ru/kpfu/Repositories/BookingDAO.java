package ru.kpfu.Repositories;

import ru.kpfu.models.Booking;


import java.util.List;

public interface BookingDAO extends CrudDAO<Booking> {

    void cancelBooking(int bookingId);
    List<Booking> getActiveBookingsByUserId(int userId);
    void updateBookingStatus(int bookingId, String newStatus);

    List<Booking> getActiveBookings();
    List<Booking> getHistoricalBookings();


}
