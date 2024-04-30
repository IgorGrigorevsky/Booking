package servlet.room;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RoomService;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/getAllRooms")
public class GetAllRoomsServlet extends HttpServlet {

    RoomService roomService = RoomService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("roomsList", roomService.findAll());
        request.getRequestDispatcher(JspHelper.getPath("getAllRooms")).forward(request, response);
    }
}