package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.UrlPath;

import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // метод invalidate() удаляет нашу сессию из ассоциативного массива, который хранится у нас
        // на сервере, делает ее невалидной -> следовательно, после этого мы уже не сможем обратиться к этой сессии,
        // нам надо будет создавать ее новую, по сути осуществлять новый login в нашей системе
        request.getSession().invalidate();
        response.sendRedirect(UrlPath.START_PAGE);
    }
}
