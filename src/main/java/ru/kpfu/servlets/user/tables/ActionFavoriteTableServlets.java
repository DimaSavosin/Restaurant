package ru.kpfu.servlets.user.tables;


import ru.kpfu.service.FavoriteTableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/favoriteTablesAdd")
public class ActionFavoriteTableServlets extends HttpServlet {
   private FavoriteTableService favoriteTableService;


    @Override
    public void init() throws ServletException {
        favoriteTableService = (FavoriteTableService) getServletContext().getAttribute("favoriteTableService");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId = (int) req.getSession().getAttribute("userId");
        int tableId = Integer.parseInt(req.getParameter("tableId"));
        String action = req.getParameter("action");

        if ("add".equalsIgnoreCase(action)) {
            favoriteTableService.addFavoriteTable(userId, tableId);
        } else if ("remove".equalsIgnoreCase(action)) {
            favoriteTableService.removeFavoriteTable(userId, tableId);
        }

        resp.sendRedirect(req.getContextPath() + "/favoriteTables");

    }
}
