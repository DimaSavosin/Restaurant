package ru.kpfu.servlets.controllers;

import ru.kpfu.servlets.dto.UserDto;
import ru.kpfu.servlets.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserListServlets extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<UserDto> userList = userService.getAllUsers();
            req.setAttribute("userList", userList);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ошибка при получении списка пользователей.");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/views/userList.jsp").forward(req, resp);
    }
}
