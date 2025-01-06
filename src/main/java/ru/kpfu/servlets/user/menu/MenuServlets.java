package ru.kpfu.servlets.user.menu;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.kpfu.service.MenuService;
import ru.kpfu.dto.menuDTO.MenuResponseDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/menuData")
public class MenuServlets extends HttpServlet {
    private MenuService menuService;


    @Override
    public void init() throws ServletException {
        menuService = (MenuService) getServletContext().getAttribute("menuService");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<MenuResponseDTO> menuList = menuService.getAllMenuWithImages();



        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(menuList);



        response.getWriter().write(json);
    }
}
