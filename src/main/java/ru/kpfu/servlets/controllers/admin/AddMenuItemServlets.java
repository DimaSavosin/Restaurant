package ru.kpfu.servlets.controllers.admin;


import ru.kpfu.servlets.dto.menuDTO.MenuRequestDTO;
import ru.kpfu.servlets.models.File;
import ru.kpfu.servlets.service.FileService;
import ru.kpfu.servlets.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/admin/addMenuItem")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 1024 * 1024 * 5,  // 5MB
        maxRequestSize = 1024 * 1024 * 10 // 10MB
)
public class AddMenuItemServlets extends HttpServlet {
    private MenuService menuService;
    private FileService fileService;

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
        req.setCharacterEncoding("UTF-8");

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        int price = Integer.parseInt(req.getParameter("price"));

        // Обработка файла
        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uploadDir = "C:/Users/user/IdeaProjects/ORIS/src/main/webapp/uploads/";
        String uniqueFileName = UUID.randomUUID() + "_" + fileName;
        String filePath = uploadDir + uniqueFileName;

// Убедитесь, что директория существует
        Files.createDirectories(Paths.get(uploadDir));
        System.out.println("Saving file to: " + filePath);

// Сохранение файла
        filePart.write(filePath);

// Составляем URL для хранения в БД
        String fileUrl = req.getContextPath() + "/uploads/" + uniqueFileName;


        // Сохранение файла в БД
        File file = File.builder()
                .name(fileName)
                .path(fileUrl) // Сохраняем полный URL
                .build();
        fileService.saveFile(file);

        // Получение ID сохраненного файла
        Optional<File> savedFile = fileService.getFileByNameAndPath(file.getName(), file.getPath());
        int fileId = savedFile.map(File::getId).orElseThrow(() -> new RuntimeException("File not saved"));

        // Сохранение блюда в БД
        MenuRequestDTO menuRequestDTO = MenuRequestDTO.builder()
                .name(name)
                .description(description)
                .price(price)
                .fileId(fileId)
                .imagePath(fileUrl) // Передаём путь изображения
                .build();
        menuService.addMenu(menuRequestDTO);
        System.out.println("Saving file to: " + filePath);


        // Перенаправление обратно на страницу добавления
        resp.sendRedirect(req.getContextPath() + "/admin/addMenuItem");
    }
}
