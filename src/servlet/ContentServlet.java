package servlet;

import dao.DaoRoom;
import entity.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JspHelper;

import java.io.IOException;
import java.util.List;

import static java.util.stream.Collectors.toMap;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {
    private  final DaoRoom daoRoom = DaoRoom.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Room> roomList = daoRoom.findAll();
        req.setAttribute("list", roomList);
        req.getSession().setAttribute("listMap", roomList.stream().collect(toMap(Room::getId, Room::toString)));

        req.getRequestDispatcher(JspHelper.getPath("content")).forward(req, resp);
    }
}
