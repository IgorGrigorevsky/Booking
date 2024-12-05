package servlet.room;


import dto.room.RoomDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RoomService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/getRoomById")
public class GetRoomByIdServlet extends HttpServlet {
    RoomService roomService = RoomService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter writer = response.getWriter()) {

            Long value = 27L;
            List<RoomDto> byId = roomService.findById(value);
            writer.write("<h1> room with id: %d</h1>".formatted(value));
            if (byId.size() >= 1) {
                writer.write("<br> %s </br>".formatted(byId.toString()));
            } else {
                writer.write("<br> номера с ID = %d  не найдено, извините =)</br>".formatted(value));
            }
        }
    }
}

