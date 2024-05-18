package servlet.room;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.HotelService;
import service.RoomClassService;
import service.RoomService;
import util.JspHelper;

import java.io.IOException;

import static util.UrlPath.GET_ALL_ROOMS;

@WebServlet(GET_ALL_ROOMS)
public class GetAllRoomsServlet extends HttpServlet {

    RoomService roomService = RoomService.getINSTANCE();
    HotelService hotelService = HotelService.getINSTANCE();
    RoomClassService roomClassService = RoomClassService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("roomsList", roomService.findAll());
        request.setAttribute("hotelList", hotelService.findAll());
        request.setAttribute("roomClass", roomClassService.findAll());

        request.getRequestDispatcher(JspHelper.getPath("getAllRooms")).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getParameter("hotel");
    }
}