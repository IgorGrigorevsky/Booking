package servlet.hotel;

import dto.hotel.CreateHotelDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.HotelService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;

import static util.UrlPath.CREATE_HOTEL;
import static util.UrlPath.GET_ALL_ROOMS;

@WebServlet(CREATE_HOTEL)
public class CreateHotelServlet extends HttpServlet {

    HotelService hotelService = HotelService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("stars", List.of("1", "2", "3", "4", "5"));
        request.getRequestDispatcher(JspHelper.getPath("createHotel")).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreateHotelDto buildHotelDto = CreateHotelDto.builder()
                .name(request.getParameter("hotelName"))
                .address(request.getParameter("address"))
                .phone(request.getParameter("phoneNumber"))
                .email(request.getParameter("email"))
                .starsId(request.getParameter("stars"))
                .build();

        hotelService.create(buildHotelDto);
        response.sendRedirect(GET_ALL_ROOMS);
    }
}
