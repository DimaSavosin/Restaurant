package ru.kpfu.servlets.controllers.user;

import ru.kpfu.servlets.dto.UserDto;
import ru.kpfu.servlets.models.Booking;
import ru.kpfu.servlets.service.BookingService;
import ru.kpfu.servlets.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/profile")
public class ProfileServlets extends HttpServlet {
    private UserService userService;
    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
        bookingService = (BookingService) getServletContext().getAttribute("bookingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userId = (Integer) req.getSession().getAttribute("userId");
        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        try {
            UserDto userDto = userService.getUserById(userId);
            List<Booking> bookings = bookingService.getActiveBookingsByUserId(userId);

            if (userDto != null) {
                req.setAttribute("user", userDto);
                req.setAttribute("bookings", bookings);
                getServletContext().getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при получении данных пользователя.");
        }
    }
}
