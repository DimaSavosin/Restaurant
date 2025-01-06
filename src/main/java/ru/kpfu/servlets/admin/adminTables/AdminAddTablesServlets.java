package ru.kpfu.servlets.admin.adminTables;


import ru.kpfu.dto.tableDTO.TableRequestDTO;
import ru.kpfu.service.TableService;


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


        req.getRequestDispatcher("/WEB-INF/views/admin/adminAddTables.jsp").forward(req, resp);


    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            int tableNumber = Integer.parseInt(req.getParameter("tableNumber"));
            int seatingCapacity = Integer.parseInt(req.getParameter("seatingCapacity"));
            String location = req.getParameter("location");

            TableRequestDTO tableRequestDTO = TableRequestDTO.builder()
                    .tableNumber(tableNumber)
                    .seatingCapacity(seatingCapacity)
                    .location(location)
                    .build();

            boolean isAdded = tableService.addTable(tableRequestDTO);

            if (!isAdded) {
                req.setAttribute("errorMessage", "Стол с таким номером уже существует.");
                req.getRequestDispatcher("/WEB-INF/views/admin/adminAddTables.jsp").forward(req, resp);
                return;
            }
            resp.sendRedirect(req.getContextPath() + "/admin/tables");



    }

}
