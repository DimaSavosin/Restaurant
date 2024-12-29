package ru.kpfu.controllers.user;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/uploads/*")
public class FileServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "C:/uploads/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Получаем имя файла из URL
        String fileName = req.getPathInfo().substring(1); // Убираем первый "/"
        File file = new File(UPLOAD_DIR + fileName);

        // Проверяем существование файла
        if (!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Файл не найден");
            return;
        }

        // Устанавливаем MIME-тип файла
        resp.setContentType(getServletContext().getMimeType(file.getName()));
        resp.setContentLengthLong(file.length());

        // Отправляем файл клиенту
        try (var fileInputStream = new FileInputStream(file);
             var outputStream = resp.getOutputStream()) {
            fileInputStream.transferTo(outputStream);
        }
    }
}
