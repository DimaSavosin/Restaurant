package ru.kpfu.controllers.admin;

import ru.kpfu.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/admin/deleteDish")
public class AdminDeleteDishServlets extends HttpServlet {
   private MenuService menuService;

    @Override
    public void init() throws ServletException {
        menuService = (MenuService) getServletContext().getAttribute("menuService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int menuId = Integer.parseInt(req.getParameter("menuId"));
            menuService.deleteDish(menuId);
            req.setAttribute("successMessage", "Блюдо успешно удалено.");
            req.getRequestDispatcher("/WEB-INF/views/admin/adminMenu.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
           throw new RuntimeException(e);
        }
    }
}
