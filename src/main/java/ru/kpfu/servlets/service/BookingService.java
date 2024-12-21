package ru.kpfu.servlets.service;

import ru.kpfu.servlets.Repositories.BookingDAO;
import ru.kpfu.servlets.models.Booking;

import java.util.List;

public class BookingService {
    BookingDAO bookingDAO;
    public BookingService(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }


    public void addBooking(Booking booking) {
        bookingDAO.save(booking);
    }

    public void cancelBooking(int bookingId) {
        bookingDAO.cancelBooking(bookingId);
    }

    public List<Booking> getActiveBookingsByUserId(int userId) {
        return bookingDAO.getActiveBookingsByUserId(userId);
    }

    public void updateBookingStatus(int bookingId, String newStatus) {
        bookingDAO.updateBookingStatus(bookingId, newStatus);
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.getAll();
    }

    public List<Booking> getAllBookingsWithUserNames() {
        return bookingDAO.getAllWithUserNames();
    }

    public List<Booking> getActiveBookings() {
        return bookingDAO.getActiveBookings();
    }

    public List<Booking> getHistoricalBookings() {
        return bookingDAO.getHistoricalBookings();
    }

    public int createBooking(int userId) {
        Booking booking = Booking.builder()
                .user_id(userId)
                .status("pending")
                .build();
        return bookingDAO.saveAndReturnId(booking); // Сохраняем и возвращаем ID
    }







}
