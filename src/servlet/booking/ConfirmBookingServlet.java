package servlet.booking;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BookingService;

import java.io.IOException;

import static util.UrlPath.CONFIRM_BOOKING;
import static util.UrlPath.NEED_TO_BOOK;

@WebServlet(CONFIRM_BOOKING)
public class ConfirmBookingServlet extends HttpServlet {
    BookingService bookingService = BookingService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        bookingService.confirm(id);
        request.getRequestDispatcher(NEED_TO_BOOK).forward(request, response);
    }
}
