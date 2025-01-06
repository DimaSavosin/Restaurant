package ru.kpfu.servlets.user.files;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/uploads/*")
public class FileServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "C:/uploads/";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String fileName = req.getPathInfo().substring(1);
        File file = new File(UPLOAD_DIR + fileName);


        if (!file.exists()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Файл не найден");
            return;
        }


        resp.setContentType(getServletContext().getMimeType(file.getName()));
        resp.setContentLengthLong(file.length());


        try (FileInputStream fileInputStream = new FileInputStream(file);
             OutputStream outputStream = resp.getOutputStream()) {
            fileInputStream.transferTo(outputStream);
        }
    }
}
