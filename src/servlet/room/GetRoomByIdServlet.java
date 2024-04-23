package servlet.room;


import dao.DaoRoom;
import entity.Room;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@WebServlet("/getRoomById")
public class GetRoomByIdServlet extends HttpServlet {
    DaoRoom daoRoom = DaoRoom.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter writer = response.getWriter()) {

            Long value = 27L;
            Optional<Room> byId = daoRoom.findById(value);
            writer.write("<h1> room with id: %d</h1>".formatted(value));
            if (byId.isPresent()) {
                writer.write("<br> %s </br>".formatted(byId.toString()));
            } else {
                writer.write("<br> номера с ID = %d  не найдено, извините =)</br>".formatted(value));
            }
        }
    }
}


//    DaoRoom daoRoom = DaoRoom.getINSTANCE();
//
//
//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        long roomID = Long.parseLong(req.getParameter("roomID"));
//
//        // получил список всех entity
////        List<Room> all = daoRoom.findAll();
////        List<DaoRoom> collect = all.stream().map(room -> new DaoRoom(room.getId(),
////                room.getHotelId(), room.getBedsCount(),
////                room.getFloor(), room.getIncludedBreakfast(), room.getPrice()))
////                .toList();
//
//
//
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//
//        try (PrintWriter printWriter = resp.getWriter();) {
//            printWriter.write("Имеющиеся комнаты");
//            printWriter.write("<ul>");
////            Optional<Room> byId =
//
//            List<Room> all1 = daoRoom.findAll();
////            System.out.println(all);
////            printWriter.write(all.toString());
////            daoRoom.findById(roomID).ifPresent(room -> printWriter.write(String.valueOf(room)));
//        }
//
//


