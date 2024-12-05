package servlet.room;

import dto.hotel.HotelDto;
import dto.room.RoomDto;
import dto.roomClass.RoomClassDto;
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
import java.util.List;

import static util.UrlPath.GET_ROOM_BY_ID_JSTL;

@WebServlet(GET_ROOM_BY_ID_JSTL)
public class GetRoomByIdJSTL extends HttpServlet {
    RoomService roomService = RoomService.getINSTANCE();
    HotelService hotelService = HotelService.getINSTANCE();
    RoomClassService roomClassService = RoomClassService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long roomId = Long.valueOf(request.getParameter("roomId"));
        List<RoomDto> roomList = roomService.findById(roomId).stream().toList();
        RoomDto firstRoom = roomList.getFirst();
        HotelDto hotel = hotelService.findById(roomList.getFirst().getHotel_id()).getFirst();
        RoomClassDto roomClass = roomClassService.findById(roomList.getFirst().getClass_id()).getFirst();
        request.setAttribute("roomById", firstRoom);
        request.setAttribute("hotelById", hotel);
        request.setAttribute("roomClass", roomClass);
        request.getRequestDispatcher(JspHelper.getPath("getRoomById")).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long roomId = Long.valueOf(request.getParameter("roomId"));
        List<RoomDto> roomList = roomService.findById(roomId).stream().toList();
        Long id = roomList.getFirst().getId();
        request.getSession().setAttribute("id", id);
        request.getRequestDispatcher(JspHelper.getPath("createBooking")).forward(request, response);
    }
}
