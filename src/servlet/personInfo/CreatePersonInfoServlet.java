package servlet.personInfo;

import dto.authentication.CreateAuthenticationDto;
import dto.authorisation.CreateAuthorisationDto;
import dto.client.CreateClientDto;
import dto.clientRating.CreateClientRatingDto;
import dto.personInfo.CreatePersonInfoDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.AuthenticationService;
import service.AuthorisationService;
import service.ClientRatingService;
import service.ClientService;
import service.PersonInfoService;
import util.JspHelper;
import util.UrlPath;

import java.io.IOException;

import static util.UrlPath.START_PAGE;

// регистрация клиента
@WebServlet(UrlPath.REGISTRATION)
public class CreatePersonInfoServlet extends HttpServlet {

    PersonInfoService personInfoService = PersonInfoService.getINSTANCE();
    AuthenticationService authenticationService = AuthenticationService.getINSTANCE();
    AuthorisationService authorisationService = AuthorisationService.getINSTANCE();
    ClientRatingService clientRatingService = ClientRatingService.getINSTANCE();
    ClientService clientService = ClientService.getINSTANCE();

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
        Long personInfoId = personInfoService.create(buildPersonInfoDto);

        CreateAuthenticationDto buildAuthenticationDto = CreateAuthenticationDto.builder()
                .personInfoId(personInfoId)
                .password(request.getParameter("password"))
                .build();
        authenticationService.create(buildAuthenticationDto);

        Integer clientRatingId = clientRatingService.create(CreateClientRatingDto.builder()
                .rating("2")
                .build());

        clientService.create(CreateClientDto.builder()
                .personInfoId(String.valueOf(personInfoId))
                .clientRatingId(String.valueOf(clientRatingId))
                .build());

        authorisationService.create(CreateAuthorisationDto.builder()
                .personInfoId(personInfoId)
                .roleId("клиент")
                .isClient(true)
                .build());

        response.sendRedirect(START_PAGE);
    }
}
