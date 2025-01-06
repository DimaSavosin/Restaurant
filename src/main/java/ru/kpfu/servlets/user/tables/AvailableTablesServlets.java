package ru.kpfu.servlets.user.tables;

import ru.kpfu.dto.tableDTO.TableResponseDTO;
import ru.kpfu.service.BookingService;
import ru.kpfu.service.FavoriteTableService;
import ru.kpfu.service.TableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@WebServlet("/availableTables")
public class AvailableTablesServlets extends HttpServlet {
    private TableService tableService;
    private BookingService bookingService;
    private FavoriteTableService favoriteTableService;

    @Override
    public void init() throws ServletException {
        tableService = (TableService) getServletContext().getAttribute("tableService");
        bookingService = (BookingService) getServletContext().getAttribute("bookingService");
        favoriteTableService = (FavoriteTableService) getServletContext().getAttribute("favoriteTableService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            int userId = (int) req.getSession().getAttribute("userId");
            String location = req.getParameter("location");
            LocalDate bookingDate = LocalDate.parse(req.getParameter("bookingDate"));
            LocalTime bookingTime = LocalTime.parse(req.getParameter("bookingTime"));
            int duration = Integer.parseInt(req.getParameter("duration"));

            if (!bookingService.isBookingTimeValid(bookingTime, duration)) {
                req.setAttribute("errorMessage", "Выбранное время бронирования выходит за рабочие часы ресторана (10:00 - 22:00).");
                req.getRequestDispatcher("/WEB-INF/views/bookings/bookingForm.jsp").forward(req, resp);
                return;
            }

            List<TableResponseDTO> availableTables = tableService.getAvailableTables(location, bookingDate, bookingTime, duration);
            List<Integer> favoriteTableIds = favoriteTableService.getFavoriteTablesID(userId);

            req.setAttribute("availableTables", availableTables);
            req.setAttribute("favoriteTableIds", favoriteTableIds);
            req.setAttribute("bookingDate", bookingDate);
            req.setAttribute("bookingTime", bookingTime);
            req.setAttribute("duration", duration);

            req.getRequestDispatcher("/WEB-INF/views/tables/availableTables.jsp").forward(req, resp);

    }
}

