package servlet.room;

import dao.DaoRoom;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/getRoomByIdJstl")
public class GetAllRoomsJSTL extends HttpServlet {

    DaoRoom daoRoom = DaoRoom.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("roomsList", daoRoom.findAll());
        request.getRequestDispatcher(JspHelper.getPath("roomAll2")).forward(request, response);
    }
}