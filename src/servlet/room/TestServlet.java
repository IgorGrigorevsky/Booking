package servlet.room;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;


@WebServlet("/test")
public class TestServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // получаем одно значение параметра
        String param = req.getParameter("param");
        // получаем все параметры, передаваемые на наш request
        Map<String, String[]> parameterMap = req.getParameterMap();
        System.out.println();

        resp.setContentType("text/html; charset=UTF-8");
        resp.setHeader("token", "12345");
        PrintWriter writer = resp.getWriter();
        writer.write("la-la-la");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // принимаем тело запроса
        // метод считывает все в качестве символов-строк
        try (BufferedReader reader = req.getReader();
             // считываем построчно
             var lines = reader.lines()) {
            lines.forEach(System.out::println);
        }
    }
}

        // метод считывает бинарный тип данных - считываем файл из нашего браузера,
        // будто он соединяется с нашим компьютером и является нашим жестким диском
        // req.getInputStream();



//        @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Map<String, String[]> parameterMap = req.getParameterMap();
//            System.out.println(parameterMap);
//    }

    //        resp.setContentType("text/html");
//        DaoRoom daoRoom = DaoRoom.getINSTANCE();
//        try (PrintWriter writer = resp.getWriter()) {
//
//            Long value = 5L;
//            Optional<Room> byId = daoRoom.findById(value);
//            writer.write("<h1> room with id: %d</h1>".formatted(value));
//
//            writer.write("<br> %s </br>".formatted(byId.toString()));



