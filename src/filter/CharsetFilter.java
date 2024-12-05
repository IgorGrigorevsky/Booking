package filter;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


// ставим URL-pattern - по которому этот фильтр будет срабатывать
@WebFilter(value = "/*",
        dispatcherTypes = DispatcherType.REQUEST)

public class CharsetFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(StandardCharsets.UTF_8.name());
        servletResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // мы установили кодировки для запроса и ответа, теперь, если нам нужен иной фильтр,
    // то мы перенаправляем этот процесс на другой фильтр, если нет, то идем на сервлет
    // и потом возвращаем ответ клиенту
    // Для этого нам и нужен объект FilterChain, который знает про всю цепочку вызовов,
    // у него есть только один метод .doFilter(req,resp), куда мы передаем запрос и ответ
}
