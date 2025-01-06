package ru.kpfu.servlets.user.tables;

import ru.kpfu.dto.tableDTO.TableResponseDTO;
import ru.kpfu.service.FavoriteTableService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@WebServlet("/favoriteTables")
public class FavoriteTableListServlets extends HttpServlet {
    private FavoriteTableService favoriteTableService;


    @Override
    public void init() throws ServletException {
        favoriteTableService = (FavoriteTableService) getServletContext().getAttribute("favoriteTableService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            int userId = (int) req.getSession().getAttribute("userId");

            List<TableResponseDTO> favoriteTables = favoriteTableService.getFavoriteTablesWithDetails(userId);

            req.setAttribute("favoriteTables", favoriteTables);
            req.getRequestDispatcher("/WEB-INF/views/tables/favoriteTables.jsp").forward(req, resp);

    }
}
