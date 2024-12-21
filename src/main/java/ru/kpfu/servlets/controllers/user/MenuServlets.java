package ru.kpfu.servlets.controllers.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.kpfu.servlets.models.File;
import ru.kpfu.servlets.models.Menu;
import ru.kpfu.servlets.service.FileService;
import ru.kpfu.servlets.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/menuData")
public class MenuServlets extends HttpServlet {
    private MenuService menuService;
    private FileService fileService;

    @Override
    public void init() throws ServletException {
        menuService = (MenuService) getServletContext().getAttribute("menuService");
        fileService = (FileService) getServletContext().getAttribute("fileService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Menu> menuList = menuService.getAllMenu();

        // Для каждого элемента меню извлекаем путь изображения
        List<Menu> enrichedMenuList = menuList.stream().map(menu -> {
            if (menu.getFileId() != null) {
                fileService.getFileById(menu.getFileId()).ifPresent(file -> menu.setImagePath(file.getPath()));
            }
            return menu;
        }).collect(Collectors.toList());

        // Устанавливаем заголовки ответа
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // Преобразуем данные в JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(enrichedMenuList);

        // Отправляем JSON клиенту
        response.getWriter().write(json);
    }
}
