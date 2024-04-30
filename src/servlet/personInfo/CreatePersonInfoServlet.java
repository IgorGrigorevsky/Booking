package servlet.personInfo;

import dto.personInfo.CreatePersonInfoDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.PersonInfoService;
import util.JspHelper;

import java.io.IOException;

@WebServlet("/createPersonInfo")
public class CreatePersonInfoServlet extends HttpServlet {

    PersonInfoService personInfoService = PersonInfoService.getINSTANCE();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspHelper.getPath("createPersonInfo")).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CreatePersonInfoDto buildPersonInfoDto = CreatePersonInfoDto.builder()
                .fullName(request.getParameter("fullName"))
                .phoneNumber(request.getParameter("phoneNumber"))
                .email(request.getParameter("email"))
                .build();

        personInfoService.create(buildPersonInfoDto);
        response.sendRedirect("/startPage");
    }
}
