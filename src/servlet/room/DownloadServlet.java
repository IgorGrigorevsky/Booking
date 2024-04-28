package servlet.room;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-Disposition", "attachments; filename=\"filename.txt\"");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());


        // так сделать мы не можем, т.к. наш ServletStarter - не самостоятельное приложение,
        // мы заворачиваем его в war-архив и deploy'ем на Tomcat, который уже является самостоятельным
        // приложением

        // Files.readAllBytes(Path.of("sources", "example.json"));

        // берем у любого класса -> .class.getClassLoader() - наш класс Servlet знает о
        // ClassLoader'е, который его загрузил. ClassLoader знает обо всех ресурсах,
        // которые у нас есть в ClassPath'е - поэтому мы можем получить stream ресурса с названием example.json.
        // но папка должна быть помечена как "resources root"


        // если мы работаем со строками-символами
//        try (PrintWriter writer = resp.getWriter();
//             InputStream stream = DownloadServlet.class.getClassLoader()
//                     .getResourceAsStream("example.json")) {
//        // т.к. writer работает с символами - преобразуем байты в строку
//        writer.write(Arrays.toString(stream.readAllBytes()));
//        }

        // или если мы хотим работать с байтами
        try (ServletOutputStream outputStream = resp.getOutputStream();
             InputStream inputStream = DownloadServlet.class.getClassLoader()
                     .getResourceAsStream("example.json")) {
            // т.к. writer работает с символами - преобразуем байты в строку
            outputStream.write(inputStream.readAllBytes());
        }
    }
}
