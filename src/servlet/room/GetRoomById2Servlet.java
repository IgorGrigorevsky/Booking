package servlet.room;

import dao.DaoRoom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


@WebServlet("/getRoomById2")
public class GetRoomById2Servlet extends HttpServlet {
    DaoRoom daoRoom = DaoRoom.getInstance();

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
            daoRoom.findById(roomId).stream().toList().forEach(room -> writer.write(
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
                               """.formatted(room.getId(), room.getHotelId(), room.getBedsCount(),
                            room.getFloor(), room.getClassId(), room.getPrice())
            ));
            writer.write("</ul>");
        }
    }
}

