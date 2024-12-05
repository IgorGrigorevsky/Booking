package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static util.UrlPath.LOCALE;
import static util.UrlPath.LOGIN;

@WebServlet(LOCALE)
public class LocaleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // получаем параметр языка
        String language = request.getParameter("lang");

        // на страничке registration или login у нас сессии не будет,
        // поэтому мы можем только получить локалию по header'у,
        // либо мы также через параметры можем передавать наш язык, который мы установили
        // либо мы также можем сделать установку в сессию, если она существует,
        // и параметром в качестве respons'а,
        // а далее при создании уже определять что и из чего брать

        // устанавливаем в атрибуты язык, полученный из параметров
        request.getSession().setAttribute("lang", language);

        String previousPage = request.getHeader("referer");

        // если previousPage нет
        String page = previousPage != null ? previousPage : LOGIN;

        // перенаправляем параметры и добавляем параметры
        response.sendRedirect(page + "?lang=" + language);
    }
}
