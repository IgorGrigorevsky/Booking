package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JspHelper;

import java.io.IOException;
import java.util.List;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    // на этот метод мы возвращаем нашу JSP страницу
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("roles", List.of("USER", "ADMIN"));
        req.setAttribute("gender", List.of("MALE", "FEMALE"));
        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req,resp);
    }

    // на этот метод мы принимаем форму и обрабатываем ее
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
    }
}
