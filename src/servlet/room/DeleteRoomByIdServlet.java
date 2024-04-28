package servlet.room;

import dao.DaoRoom;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

//@WebServlet("/DeleteRoomByIdServlet")
public class DeleteRoomByIdServlet extends HttpServlet {



//    DaoRoom daoRoom = DaoRoom.getINSTANCE();
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        Long roomId = Long.parseLong(req.getParameter("id"));
//
//        daoRoom.delete(roomId);
//        resp.sendRedirect(req.getContextPath() + "/roomServlet");
//
//    }
}
