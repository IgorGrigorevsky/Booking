package servlet;

import dto.personInfo.PersonInfoDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import service.PersonInfoService;
import util.JspHelper;
import util.UrlPath;

import java.io.IOException;

@WebServlet(UrlPath.LOGIN)
public class Login extends HttpServlet {

    PersonInfoService personInfoService = PersonInfoService.getINSTANCE();

    // для возвращения JSP страницы - "логин"
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspHelper.getPath("login")).forward(request, response);
    }

    // если мы отправляем форму с нашей страницы "логин", нажимая кнопку button, к нам на login отправляется форма методом POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // если логин успешный, то мы добавляем его в сессию, т.е. наш пользователь залогинен
        // и он должен быть в scope'е sesion request, чтобы в последующем мы могли его получать из любого нашего сервлета
        // в противном случае перенаправляем обратно на логин-страницу
        personInfoService.login(email, password)
                .ifPresentOrElse(personInfoDto -> onLogginSuccess(personInfoDto, request, response),
                        // в противном случае, мы должны передать Runnable
                        () -> onLogginFail(request, response)
                );
    }

    // т.к. в лямбда-выражениях обработать ошибку не получиться - е нужно или ловить или мы пишем метод

    //    personInfoDto -> {
    //        request.getSession().setAttribute("personInfo", personInfoDto);
    //        response.sendRedirect("/getAllRooms");
    //   }
    @SneakyThrows
    private void onLogginSuccess(PersonInfoDto personInfoDto, HttpServletRequest request, HttpServletResponse response) {

        request.getSession().setAttribute("personInfo", personInfoDto);
        response.sendRedirect("/getAllRooms");
    }

    // создаем метод и страницу для неверного логгирования
    @SneakyThrows
    private void onLogginFail(HttpServletRequest request, HttpServletResponse response) {
        response.sendRedirect("/login?error&email=" + request.getParameter("email"));

    }
}

