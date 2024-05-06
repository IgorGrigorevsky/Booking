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

// фильтр для проверки определенного URL - и если юзер не залогирован,
// т.е. нет объекта юзер в сессии, то мы его перенаправляем на другой URL
@WebFilter("/unsafe")
public class UnsafeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // т.к. все объекты наших атрибутов - это Object, поэтому мы явно приводим к нужному типу
        Object user = (PersonInfoDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");

        if (user != null) {
            // тогда продолжаем выполнение нашей странички
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else {
            ((HttpServletResponse) servletResponse).sendRedirect("/startPage");
        }
    }
}
