package ru.kpfu.servlets.controllers.admin;

import ru.kpfu.servlets.models.Tables;
import ru.kpfu.servlets.service.TableService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/tables")
public class AdminAddTablesServlets extends HttpServlet {
    private TableService tableService;
    @Override
    public void init() throws ServletException {
        tableService = (TableService) getServletContext().getAttribute("tableService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/WEB-INF/views/admin/adminAddTables.jsp").forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int tableNumber = Integer.parseInt(req.getParameter("tableNumber"));
        int seatingCapacity = Integer.parseInt(req.getParameter("seatingCapacity"));
        String location = req.getParameter("location");

        Tables table = Tables.builder().tableNumber(tableNumber).seatingCapacity(seatingCapacity).location(location).build();
        tableService.addTable(table);
        resp.sendRedirect(req.getContextPath() + "/admin/tables");


    }

}
