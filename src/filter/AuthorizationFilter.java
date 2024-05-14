package filter;

import dto.personInfo.PersonInfoDto;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static util.UrlPath.CREATE_HOTEL;
import static util.UrlPath.GET_ROOM_BY_ID_JSTL;
import static util.UrlPath.LOCALE;
import static util.UrlPath.LOGIN;
import static util.UrlPath.REGISTRATION;
import static util.UrlPath.START_PAGE;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(START_PAGE, LOGIN, REGISTRATION, LOCALE, CREATE_HOTEL);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // метод getRequestURI() - возвращает именно путь в нашем URL
        // и на основании pass мы можем указать, что разрешено пользователю, а что не разрешено
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();

        if (isPublicPath(requestURI) || isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // header referer - говорит о том, с какой странички пользователь пришел на нашу страничку к которой сработал фильтр
            String previousPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse).sendRedirect(previousPage != null ? previousPage : START_PAGE);
        }
    }

    // из атрибутов сессии request'а берем
    private static boolean isUserLoggedIn(ServletRequest servletRequest) {
        PersonInfoDto personInfoDto = (PersonInfoDto) ((HttpServletRequest) servletRequest)
                .getSession().getAttribute("personInfo");
        return personInfoDto != null;
    }

    private static boolean isPublicPath(String requestURI) {
        return PUBLIC_PATH.stream().anyMatch(path -> requestURI.startsWith(path));
    }
}
