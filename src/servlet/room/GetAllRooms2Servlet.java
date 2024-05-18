package servlet.room;

import dto.room.RoomDto;
import dto.room.RoomFilter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RoomService;
import util.JspHelper;

import java.io.IOException;
import java.util.List;

@WebServlet("/getAllRooms2")
public class GetAllRooms2Servlet extends HttpServlet {
    RoomService roomService = RoomService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Long hotelid = Long.valueOf(request.getParameter("hotel"));
        List<RoomDto> listById = roomService.findAllWithFilters(new RoomFilter(100, 0, null, hotelid, null, null,
                null, null, null));
        request.setAttribute("roomListById", listById);

        request.getRequestDispatcher(JspHelper.getPath("filterHotelById")).forward(request, response);
    }
}
