package ru.kpfu.servlets.controllers;

import ru.kpfu.servlets.models.Booking;
import ru.kpfu.servlets.models.Tables;
import ru.kpfu.servlets.service.BookingService;
import ru.kpfu.servlets.service.TableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebServlet("/bookingTable")
public class BookingServlets extends HttpServlet {
    private TableService tableService;
    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        tableService = (TableService) getServletContext().getAttribute("tableService");
        bookingService = (BookingService) getServletContext().getAttribute("bookingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String location = req.getParameter("location");
        LocalDate bookingDate = LocalDate.parse(req.getParameter("bookingDate"));
        LocalTime bookingTime = LocalTime.parse(req.getParameter("bookingTime"));
        int duration = Integer.parseInt(req.getParameter("duration"));


        List<Tables> availableTables = tableService.getAvailableTables(location, bookingDate, bookingTime, duration);

        req.setAttribute("availableTables", availableTables);
        req.setAttribute("bookingDate", bookingDate);
        req.setAttribute("bookingTime", bookingTime);
        req.setAttribute("duration", duration);

        req.getRequestDispatcher("/WEB-INF/views/availableTables.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Обработка POST-запроса: бронирование стола
        int userId = (int) req.getSession().getAttribute("userId");
        int tableId = Integer.parseInt(req.getParameter("tableId"));
        LocalDate bookingDate = LocalDate.parse(req.getParameter("bookingDate"));
        LocalTime bookingTime = LocalTime.parse(req.getParameter("bookingTime"));
        int duration = Integer.parseInt(req.getParameter("duration"));

        Booking booking = Booking.builder()
                .user_id(userId)
                .tableId(tableId)
                .bookingDate(bookingDate)
                .bookingTime(bookingTime)
                .duration(duration)
                .status("pending")
                .build();

        bookingService.addBooking(booking);

        req.getRequestDispatcher("/WEB-INF/views/bookingSuccess.jsp").forward(req, resp);
    }
}
