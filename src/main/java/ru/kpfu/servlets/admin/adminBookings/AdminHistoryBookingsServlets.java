package ru.kpfu.servlets.admin.adminBookings;


import ru.kpfu.service.BookingService;
import ru.kpfu.dto.bookingDTO.BookingResponseDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/bookingHistory")
public class AdminHistoryBookingsServlets extends HttpServlet {
    private BookingService bookingService;


    @Override
    public void init() throws ServletException {

        bookingService = (BookingService) getServletContext().getAttribute("bookingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


            List<BookingResponseDTO> historicalBookings = bookingService.getHistoricalBookings();
            req.setAttribute("historicalBookings", historicalBookings);


            req.getRequestDispatcher("/WEB-INF/views/admin/adminHistoryBookings.jsp").forward(req, resp);

    }
}


