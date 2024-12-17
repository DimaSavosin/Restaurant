package ru.kpfu.servlets.controllers.admin;

import ru.kpfu.servlets.service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/admin/bookings/confirm")
public class AdminConfirmBookingServlet extends HttpServlet {
    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        super.init();
        bookingService = (BookingService) getServletContext().getAttribute("bookingService");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int bookingId = Integer.parseInt(req.getParameter("bookingId"));

        bookingService.updateBookingStatus(bookingId,"confirmed");

        resp.sendRedirect(req.getContextPath() + "/admin/bookings");
    }
}
