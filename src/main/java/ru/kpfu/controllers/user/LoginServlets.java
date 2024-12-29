package ru.kpfu.controllers.user;

import ru.kpfu.dto.userDTO.UserDTO;
import ru.kpfu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlets extends HttpServlet {
    private UserService userService;
    @Override
    public void init() throws ServletException {
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            UserDTO userDto = userService.loginUser(email, password);
            if (userDto != null) {
                req.getSession().setAttribute("userId", userDto.getId());
                req.getSession().setAttribute("name", userDto.getName());
                req.getSession().setAttribute("role", userDto.getRole());

                if ("admin".equalsIgnoreCase(userDto.getRole())) {
                    resp.sendRedirect(req.getContextPath() + "/admin/dashboard");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/mainPage");
                }
            } else {
                req.setAttribute("errorMessage", "Неверная почта или пароль. Попробуйсте снова");
                getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            }
            } catch (Exception e) {

                throw new RuntimeException(e);
            }
        }

    }
