package servlet;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("/getRoomById");

        // в клиенте увидим изменения url
    }
}





//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // получаем объект типа RequestDispatcher с помощью метода .getRequestDispatcher() у request'a -
//        // в параметры сразу передаем url - куда будем перенаправлять наш запрос
//        req.getRequestDispatcher("/getRoomById")
//                .include(req, resp);
//
//        PrintWriter writer = resp.getWriter();
//        writer.write("Hello 2");
//    }
//}


//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // получаем объект типа RequestDispatcher с помощью метода .getRequestDispatcher() у request'a -
//        // в параметры сразу передаем url - куда будем перенаправлять наш запрос
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/getRoomById");
//                            // .forward(req, resp);
//                            // .include()
//        // мы можем установить аттрибуты для обработки на другом сервлете
////        req.setAttribute("1","123");
//        requestDispatcher.forward(req, resp);
//    }
//}
