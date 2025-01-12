package ru.kpfu.servlets.user.account;

import ru.kpfu.dto.userDTO.RegisterForm;
import ru.kpfu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlets extends HttpServlet {
  private UserService userService;
  @Override
  public void init() throws ServletException {
      userService = (UserService) getServletContext().getAttribute("userService");
  }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String password = req.getParameter("password");




            RegisterForm registerForm = new RegisterForm(name, email, password);
            boolean isRegistered = userService.registerUser(registerForm);

            if (!isRegistered) {
                req.setAttribute("errorMessage", "Эта почта уже занята");
                req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
                return;
            }


            int userId = userService.getUserIdByEmail(email);
            if (userId == -1) {
                req.setAttribute("errorMessage", "Во время регистрации произошла ошибка. Пожалуйста, попробуйте снова.");
                req.getRequestDispatcher("/WEB-INF/views/users/registration.jsp").forward(req, resp);
                return;
            }


            req.getSession().setAttribute("userId", userId);


            resp.sendRedirect(req.getContextPath() + "/mainPage");


    }
}
