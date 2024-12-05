package servlet.booking;

import dto.booking.BookingDto;
import dto.booking.BookingFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BookingService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;

import static util.UrlPath.NEED_TO_BOOK;

@WebServlet(NEED_TO_BOOK)
public class NeedToBookServlet extends HttpServlet {

    BookingService bookingService = BookingService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookingDto> bookList = bookingService.findAllWithFilters(new BookingFilter(100, 0, null, null, null, null,
                null, false, null));
        if (!bookList.isEmpty()) {
            request.setAttribute("bookList", bookList);
        }
        request.getRequestDispatcher(JspHelper.getPath("toBookList")).forward(request, response);
    }
}
