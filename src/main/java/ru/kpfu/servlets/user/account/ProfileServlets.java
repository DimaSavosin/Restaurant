package ru.kpfu.servlets.user.account;


import ru.kpfu.dto.bookingDTO.BookingResponseDTO;
import ru.kpfu.dto.userDTO.UserDTO;
import ru.kpfu.service.BookingService;
import ru.kpfu.service.FavoriteTableService;
import ru.kpfu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/profile")
public class ProfileServlets extends HttpServlet {
    private UserService userService;
    private BookingService bookingService;
    private FavoriteTableService favoriteTableService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
        bookingService = (BookingService) getServletContext().getAttribute("bookingService");
        favoriteTableService = (FavoriteTableService) getServletContext().getAttribute("favoriteTableService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");


            Optional<UserDTO> userDto = userService.getUserById(userId);
            List<BookingResponseDTO> bookings = bookingService.getActiveBookingsByUserId(userId);
            List<Integer> favoriteTableIds = favoriteTableService.getFavoriteTablesID(userId);
            if (userDto.isPresent()) {
                req.setAttribute("user", userDto.get());
                req.setAttribute("bookings", bookings);
                req.setAttribute("favoriteTableIds", favoriteTableIds);
                getServletContext().getRequestDispatcher("/WEB-INF/views/users/profile.jsp").forward(req, resp);
            }

    }
}
