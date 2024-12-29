package ru.kpfu.controllers.user;

import ru.kpfu.service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cancelBooking")
public class CancelBookingServlets extends HttpServlet {
    private BookingService bookingService;


    @Override
    public void init() throws ServletException {

        bookingService = (BookingService) getServletContext().getAttribute("bookingService");
    }


    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            int bookingId = Integer.parseInt(req.getParameter("bookingId"));


            bookingService.cancelBooking(bookingId);


            resp.sendRedirect(req.getContextPath() + "/profile");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}