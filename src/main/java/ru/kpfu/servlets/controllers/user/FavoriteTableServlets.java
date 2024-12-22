package ru.kpfu.servlets.controllers.user;


import ru.kpfu.servlets.dto.tableDTO.TableResponseDTO;
import ru.kpfu.servlets.service.FavoriteTableService;
import ru.kpfu.servlets.service.TableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/favoriteTables")
public class FavoriteTableServlets extends HttpServlet {
    private FavoriteTableService favoriteTableService;
    private TableService tableService;

    @Override
    public void init() throws ServletException {
        favoriteTableService = (FavoriteTableService) getServletContext().getAttribute("favoriteTableService");
        tableService = (TableService) getServletContext().getAttribute("tableService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = (int) req.getSession().getAttribute("userId");

        List<Integer> favoriteTableIds = favoriteTableService.getFavoriteTables(userId);
        List<TableResponseDTO> favoriteTables = favoriteTableIds.stream()
                .map(tableService::getTableById)
                .collect(Collectors.toList());


        req.setAttribute("favoriteTables", favoriteTables);
        req.getRequestDispatcher("/WEB-INF/views/favoriteTables.jsp").forward(req, resp);
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

        resp.sendRedirect(req.getContextPath() + "/profile");
    }
}
