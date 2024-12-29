package ru.kpfu.controllers.admin;

import ru.kpfu.dto.userDTO.UserDTO;
import ru.kpfu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class AdminUserListServlets extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            List<UserDTO> userList = userService.getAllUsers();
            req.setAttribute("userList", userList);


            getServletContext().getRequestDispatcher("/WEB-INF/views/admin/userList.jsp").forward(req, resp);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }
}
