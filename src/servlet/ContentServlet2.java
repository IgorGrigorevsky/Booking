package servlet;

import dao.DaoRoom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/roomJstl")
public class ContentServlet2 extends HttpServlet {
    DaoRoom daoRoom = DaoRoom.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list", daoRoom.findAll());
        req.getRequestDispatcher(JspHelper.getPath("roomJSTL")).forward(req, resp);
    }
}
