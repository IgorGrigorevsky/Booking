package servlet.booking;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static util.UrlPath.AFTER_BOOKING;
import static util.UrlPath.GET_ALL_ROOMS;

@WebServlet(AFTER_BOOKING)
public class AfterBookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(GET_ALL_ROOMS).forward(request, response);
    }
}
