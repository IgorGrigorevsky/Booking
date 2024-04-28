package servlet.room;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.RoomService;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/getAllRooms2")
public class GetAllRooms2Servlet extends HttpServlet {
    private static final RoomService roomService = RoomService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1> Список комнат </h1>");
            writer.write("<ul>");
            roomService.findAll().forEach(roomDto -> writer.write(
                    """
                            <li> 
                            {Номер отеля: 
                            <a href="/getRoomById2?roomId=%d"> |
                             
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
            writer.write("/<ul>");
        }
    }
}
