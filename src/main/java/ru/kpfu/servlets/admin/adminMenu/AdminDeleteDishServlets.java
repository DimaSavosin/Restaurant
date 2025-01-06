package ru.kpfu.servlets.admin.adminMenu;

import ru.kpfu.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/menuDelete")
public class AdminDeleteDishServlets extends HttpServlet {
    private MenuService menuService;
    @Override
    public void init() throws ServletException {
        menuService = (MenuService) getServletContext().getAttribute("menuService");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            int menuId = Integer.parseInt(req.getParameter("menuId"));
            menuService.deleteDish(menuId);
            resp.sendRedirect(req.getContextPath() + "/admin/menu");

    }
}

