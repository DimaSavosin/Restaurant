package ru.kpfu.servlets.admin.adminBookings;


import ru.kpfu.service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/bookingsAction")
public class AdminActiveBookingsActionServlets extends HttpServlet {
    private BookingService bookingService;


    @Override
    public void init() throws ServletException {

        bookingService = (BookingService) getServletContext().getAttribute("bookingService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            int bookingId = Integer.parseInt(req.getParameter("bookingId"));
            String action = req.getParameter("action");

            if ("confirm".equalsIgnoreCase(action)) {
                bookingService.updateBookingStatus(bookingId, "confirmed");
            } else if ("cancel".equalsIgnoreCase(action)) {
                bookingService.updateBookingStatus(bookingId, "cancelled");
            }


            resp.sendRedirect(req.getContextPath() + "/admin/bookings");

    }
}


