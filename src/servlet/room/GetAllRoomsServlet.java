package servlet.room;

import dto.room.RoomDto;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RoomService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getAllRooms")
public class GetAllRoomsServlet extends HttpServlet {
    private static final RoomService roomService = RoomService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {

            List<RoomDto> all = roomService.findAll();
            writer.write("<h1> All rooms from all hotels </h1>");
            for (RoomDto room : all) {
                writer.write("<br> " + room + " </br>");
            }
        }
    }
}

