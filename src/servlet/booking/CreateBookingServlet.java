package servlet.booking;

import dto.booking.CreateBookingDto;
import dto.client.ClientFilter;
import dto.personInfo.PersonInfoDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.BookingService;
import service.ClientService;
import util.JspHelper;
import util.LocalDateFormatter;

import java.io.IOException;

import static util.UrlPath.CREATE_BOOKING;

@WebServlet(CREATE_BOOKING)
public class CreateBookingServlet extends HttpServlet {

    BookingService bookingService = BookingService.getINSTANCE();
    ClientService clientService = ClientService.getINSTANCE();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object attribute = request.getSession().getAttribute("id");
        String roomId = attribute.toString();
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");
        PersonInfoDto user = (PersonInfoDto) request.getSession().getAttribute("personInfo");
        Long personInfoId = user.getId();
        Long clientId = clientService.findAllWithFilters
                        (new ClientFilter(1, 0, null, personInfoId, null))
                .getLast().getId();
        if (LocalDateFormatter.isBefore(dateFrom) && LocalDateFormatter.isBefore(dateTo)) {
            bookingService.create(CreateBookingDto.builder()
                    .clientId(clientId.toString())
                    .roomId(roomId)
                    .dateFrom(dateFrom)
                    .dateTo(dateTo)
                    .isApproved("false")
                    .isPaid("false")
                    .build());
            request.getRequestDispatcher(JspHelper.getPath("/afterBooking")).forward(request, response);
        } else {
            request.getRequestDispatcher(JspHelper.getPath("/badDate")).forward(request, response);
        }

    }
}
