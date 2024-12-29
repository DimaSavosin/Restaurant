package ru.kpfu.controllers.admin;

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
@MultipartConfig()
public class AdminAddDishServlets extends HttpServlet {
    private MenuService menuService;
    private FileService fileService;
    private static final String UPLOAD_DIR = "C:/uploads/";

    @Override
    public void init() throws ServletException {
        menuService = (MenuService) getServletContext().getAttribute("menuService");
        fileService = (FileService) getServletContext().getAttribute("fileService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/views/admin/adminAddMenuItem.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try {
            // Извлечение параметров запроса
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            int price = Integer.parseInt(req.getParameter("price"));

            // Обработка файла
            Part filePart = req.getPart("file");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String uniqueFileName = UUID.randomUUID() + "_" + fileName;
            String filePath = UPLOAD_DIR + uniqueFileName;

            // Сохранение файла на диск
            filePart.write(filePath);

            // Составляем URL для сохранения в БД
            String fileUrl = req.getContextPath() + "/uploads/" + uniqueFileName;

            // Сохранение файла в БД
            FileRequestDTO fileRequestDTO = FileRequestDTO.builder()
                    .name(fileName)
                    .path(fileUrl)
                    .build();
            fileService.saveFile(fileRequestDTO);

            // Получение ID сохраненного файла
            Optional<FileResponseDTO> savedFile = fileService.getFileByNameAndPath(fileRequestDTO.getName(), fileRequestDTO.getPath());
            int fileId = savedFile.map(FileResponseDTO::getId).orElseThrow(() -> new RuntimeException("Файл не сохранён"));

            // Сохранение блюда в БД
            MenuRequestDTO menuRequestDTO = MenuRequestDTO.builder()
                    .name(name)
                    .description(description)
                    .price(price)
                    .fileId(fileId)
                    .imagePath(fileUrl)
                    .build();
            boolean isAdded = menuService.addMenu(menuRequestDTO);

            if (!isAdded) {
                req.setAttribute("errorMessage", "Блюдо с таким названием уже существует.");
                req.getRequestDispatcher("/WEB-INF/views/admin/adminAddMenuItem.jsp").forward(req, resp);
                return;
            }


            resp.sendRedirect(req.getContextPath() + "/admin/addDish");

        } catch (Exception e) {
           throw new RuntimeException(e);
        }


    }
}
