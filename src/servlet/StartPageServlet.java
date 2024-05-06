package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JspHelper;
import util.UrlPath;

import java.io.IOException;

@WebServlet(UrlPath.START_PAGE)
public class StartPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspHelper.getPath("startPage")).forward(request,response);
    }
}
