package servlet.room;

import dao.DaoRoom;
import entity.Room;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/getAllRooms")
public class GetAllRoomsServlet extends HttpServlet {
    DaoRoom daoRoom = DaoRoom.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        try (PrintWriter writer = response.getWriter()) {

            List<Room> all = daoRoom.findAll();
            writer.write("<h1> All rooms from all hotels </h1>");
            for (Room room : all) {
                writer.write("<br> " + room + " </br>");
            }
        }
    }
}

