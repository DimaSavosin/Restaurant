package ru.kpfu.servlets.admin.adminMenu;

import ru.kpfu.dto.fileDTO.FileRequestDTO;
import ru.kpfu.dto.fileDTO.FileResponseDTO;
import ru.kpfu.dto.menuDTO.MenuRequestDTO;
import ru.kpfu.service.FileService;
import ru.kpfu.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/admin/addDish")
@MultipartConfig
public class AdminAddDishServlets extends HttpServlet {
    private MenuService menuService;
    private FileService fileService;


    @Override
    public void init() throws ServletException {
        menuService = (MenuService) getServletContext().getAttribute("menuService");
        fileService = (FileService) getServletContext().getAttribute("fileService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/views/admin/adminAddDish.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");



            String name = req.getParameter("name");
            String description = req.getParameter("description");
            int price = Integer.parseInt(req.getParameter("price"));
            Part filePart = req.getPart("file");


            FileResponseDTO savedFile = fileService.saveAndReturnFile(filePart);


            MenuRequestDTO menuRequestDTO = MenuRequestDTO.builder()
                    .name(name)
                    .description(description)
                    .price(price)
                    .fileId(savedFile.getId())
                    .imagePath(savedFile.getPath())
                    .build();


            boolean isAdded = menuService.addMenu(menuRequestDTO);

            if (isAdded) {
                resp.getWriter().write("{\"status\":\"success\",\"message\":\"Блюдо добавлено успешно\"}");
            } else {
                resp.getWriter().write("{\"status\":\"error\",\"message\":\"Блюдо с таким названием уже существует\"}");
            }

    }
}