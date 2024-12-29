package ru.kpfu.controllers.admin;

import ru.kpfu.service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/bookings/action")
public class AdminBookingActionServlets extends HttpServlet {
    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        bookingService = (BookingService) getServletContext().getAttribute("bookingService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int bookingId = Integer.parseInt(req.getParameter("bookingId"));
            String action = req.getParameter("action");

            if ("confirm".equalsIgnoreCase(action)) {
                bookingService.updateBookingStatus(bookingId, "confirmed");
            } else if ("cancel".equalsIgnoreCase(action)) {
                bookingService.updateBookingStatus(bookingId, "cancelled");
            }


            resp.sendRedirect(req.getContextPath() + "/admin/bookings");
        } catch (Exception e) {
            // Глобальная ошибка: любые другие непредвиденные проблемы
            throw new RuntimeException(e);
        }
    }
}

