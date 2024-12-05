package servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/cookies")
public class CookieServlet extends HttpServlet {

    // константа для проверки пользователя
    private static final String UNIQUE_ID = "userId";

    // мы должны использовать потокобезопасные классы, т.к. это будет общим полем для всех запросов,
    // которые идут в doGet, а CookieServlet у нас Singletone, соответственно мы используем только потокобезопасные классы, коллекции и т.д.
    private static final AtomicInteger counter = new AtomicInteger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // данный вариант не удобен, т.к. на выходе будет строка (ключ=значение),
        // которую надо будет распарсить.
//        req.getHeader("Cookie");

        // данный метод возвращает массив Cookie - но он может быть null, если не пришло ни одно значение Cookie
        Cookie[] cookies = req.getCookies();

        // пишем null-save код, который проверяет значение, которое мы сами придумаем и укажем выше,
        // чтобы определить - был ли этот пользователь уже на нашем сайте или нет

        if (cookies==null || Arrays.stream(cookies)
                .filter(cookie -> UNIQUE_ID.equals(cookie.getName()))
                .findFirst()
                .isEmpty()) {// у нас нет такой Cookie, значит пользователь у нас впервые,
            // а мы должны создать Cookie, синкрементировать наш счетчик
            // уникальных пользователей и отправить клиенту, поэтому мы создаем эту Cookie
            // название подставляем из переменной, а значение сейчас роли не играет
            Cookie cookie = new Cookie(UNIQUE_ID, "1");

            //  передаем path к которому мы обращаемся на сервере,
            // чтобы данные Cookie шли только на определенный путь
            cookie.setPath("/cookies");

            // определяется в секундах. Если мы не задаем значение,
            // то ставится дефолтное (равно -1), которое означает, что cookies будет доступна до тех пор,
            // пока браузер не закроется
            cookie.setMaxAge(15*60);

            // если мы не хотим, чтобы со стороны JavaScript'а имели доступ к этим cookies
            cookie.setHttpOnly(true);

            // если хотим, чтобы Cookie отправлялись только по HTTPS
            cookie.setSecure(false);

            // объект типа response преобразует Cookie в Header с названием Set-Cookie
            // и преобразует все наши параметры в объекте Cookie (которые мы установили) через точку с запятой
            resp.addCookie(cookie);
            counter.incrementAndGet();
        }
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (PrintWriter writer = resp.getWriter()) {
            writer.write(counter.get());
        }
    }
}
