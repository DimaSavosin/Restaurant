package ru.kpfu.servlets.user.bookings;


import ru.kpfu.service.BookingService;
import ru.kpfu.dto.bookingDTO.BookingRequestDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

@WebServlet("/bookingTable")
public class AddBookingServlets extends HttpServlet {
    private BookingService bookingService;


    @Override
    public void init() throws ServletException {
        bookingService = (BookingService) getServletContext().getAttribute("bookingService");

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            int userId = (int) req.getSession().getAttribute("userId");
            int tableId = Integer.parseInt(req.getParameter("tableId"));
            LocalDate bookingDate = LocalDate.parse(req.getParameter("bookingDate"));
            LocalTime bookingTime = LocalTime.parse(req.getParameter("bookingTime"));
            int duration = Integer.parseInt(req.getParameter("duration"));


            BookingRequestDTO bookingRequestDTO = BookingRequestDTO.builder()
                    .userId(userId)
                    .tableId(tableId)
                    .bookingDate(bookingDate)
                    .bookingTime(bookingTime)
                    .duration(duration)
                    .status("pending")
                    .build();

            bookingService.addBooking(bookingRequestDTO);

            req.getRequestDispatcher("/WEB-INF/views/bookings/bookingSuccess.jsp").forward(req, resp);

    }
}
