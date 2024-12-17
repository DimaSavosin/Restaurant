package ru.kpfu.servlets.controllers.admin;

import ru.kpfu.servlets.models.Booking;
import ru.kpfu.servlets.service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/bookings")
public class AdminBookingServlet extends HttpServlet {
    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        bookingService = (BookingService) getServletContext().getAttribute("bookingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Booking> bookings = bookingService.getAllBookings();
        req.setAttribute("bookings", bookings);
        req.getRequestDispatcher("/WEB-INF/views/admin/admin_bookings.jsp").forward(req, resp);
    }
}


