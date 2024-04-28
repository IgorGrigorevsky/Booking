package servlet.room;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/roomServlet")
public class RoomServlet extends HttpServlet {

    // первый шаг - .init(), где мы можем проинициализировать наш сервлет
    // (создать конструктор мы не можем, т.к. через Reflection API вызывается конструктор без параметров)

    // ServletConfig config - это всего лишь конфигурационный класс, в котором есть настройки нашего сервлета
    // такие как:
    // --- получение имени;

    // --- получение параметров (набор строк, которые мы можем передавать при создании сервлета) -
    // на практике редко используется;

    // ---  получение servletContext - это что-то вроде глобальной переменной на все наше приложение,
    // у всех сервлетов будет один и тот же экземпляр класса ServletContext
    // он служит для того, чтобы могли установить какие-то глобальные переменные - "Атрибуты",
    // добавлять какие-то фильтры и т.д. Данный объект знает обо всем и обо всех
    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
    }


//    // в параметрах запрос от нашего пользователя и ответ, который мы должны вернуть
//    // класс Service - это метод, который имеет кучу if-else на каждый из http-методов
//    // правило хорошего тона говорит, что мы должны переопределить не меотд service, а методы doGet, doPost и т.д.
//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
//    }


    // вернем нашему браузеру html-страницу с текстом hello world
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> headerNames = req.getHeaderNames();
        // проходимся по всем header'ам
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            // для получения значения по этому header'у
            System.out.println(req.getHeader(header));
        }


        // так как мы хотим вернуть html-страницу сначала устанавливаем несколько header'ов


        // устанавливаем MIME-type
        resp.setContentType("text/html; charset=UTF-8");
        // строкой выше мы передаем несколько header'ов, а можем передавать их отдельно
        resp.setCharacterEncoding("UTF-8");

        // в выходной поток записываем нашу html
        // resp.getOutputStream(); - но если хотим работать не с байтами, а строками, то используем другой метод
        try (PrintWriter printWriter = resp.getWriter();) {
            printWriter.write("Привет-Сервлет");
        }
    }

    // метод для удаления ресурсов. Вызывается при завершении работы
    // Tomcat'а либо undeploy нашего приложения. Если работы Tomcat'а завершится
    // аварийно, метод не вызовется
    @Override
    public void destroy() {
        super.destroy();
    }
}
