package servlet.room;

import dao.DaoRoom;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/getAllRooms2")
public class GetAllRooms2Servlet extends HttpServlet {
    DaoRoom daoRoom = DaoRoom.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter writer = response.getWriter()) {
            writer.write("<h1> Список комнат </h1>");
            writer.write("<ul>");
            daoRoom.findAll().forEach(room -> writer.write(
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
                               """.formatted(room.getId(), room.getHotelId(), room.getBedsCount(),
                            room.getFloor(), room.getClassId(), room.getPrice())
            ));
            writer.write("/<ul>");
        }
    }
}
