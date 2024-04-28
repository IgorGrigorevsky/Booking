package servlet.room;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RoomService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


@WebServlet("/getRoomById2")
public class GetRoomById2Servlet extends HttpServlet {
    RoomService roomService = RoomService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long roomId = Long.valueOf(request.getParameter("roomId"));
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        // <ul> </ul> - означает список
        // <li> </li> - означает каждое значение этого списка
        // <a href> </a> %s- ссылка на другой сервлет
        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1> Комната </h1>");
            writer.write("<ul>");
            roomService.findById(roomId).stream().toList().forEach(roomDto -> writer.write(
                    """
                            <li>
                            значение id номера: %d |
                            значение id отеля: %d |
                            количество кроватей: %d | 
                            этаж: %d |
                            наличие завтрака: 
                            значение id класса комнаты: %d |
                            цена: %d} </a>
                            </li>
                               """.formatted(roomDto.getId(), roomDto.getHotel_id(), roomDto.getBeds_count(),
                            roomDto.getFloor(), roomDto.getClass_id(), roomDto.getPrice())
            ));
            writer.write("</ul>");
        }
    }
}

