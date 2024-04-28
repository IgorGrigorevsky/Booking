package servlet;

import dto.UserDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("/sessions")
public class SessionServlet extends HttpServlet {

    private static String USER = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // т.к. каждый request знает про сессию, к которой он относится, у него есть метод .getSession()
        // если мы не передаем параметр, то он автоматически устанавливается как true
        // данный параметр говорит о том, создавать сессию, если ее не оказалось в нашем map, либо нет
        // по умолчанию мы хотим ее создать, если ее там не оказалось
        HttpSession session = req.getSession();

        // Если в атрибутах у нас есть юзер, то мы уже установили эту сессию пользователя,
        // и он пришел к нам не впервые
        // мы должны делать явное преобразование типов
        Object user = (UserDto) session.getAttribute(USER);

        if (user == null) {
            user = UserDto.builder()
                    .id(25L)
                    .mail("test@gmail.com")
                    .build();

            // под ключем USER - кладем нашего user'а
            session.setAttribute(USER,user);
        }
    }
}
