package ru.kpfu.servlets.admin.adminBookings;

import ru.kpfu.dto.bookingDTO.BookingResponseDTO;
import ru.kpfu.service.BookingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/admin/bookings")
public class AdminActiveBookingsListServlets extends HttpServlet {
    private BookingService bookingService;


    @Override
    public void init() throws ServletException {

        bookingService = (BookingService) getServletContext().getAttribute("bookingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



            List<BookingResponseDTO> activeBookings = bookingService.getActiveBookings();
            req.setAttribute("activeBookings", activeBookings);


            req.getRequestDispatcher("/WEB-INF/views/admin/adminBookings.jsp").forward(req, resp);


    }
}
