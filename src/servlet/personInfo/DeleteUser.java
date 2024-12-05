package servlet.personInfo;

import dto.client.ClientFilter;
import dto.personInfo.PersonInfoDto;
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
import util.UrlPath;

import java.io.IOException;

import static util.UrlPath.DELETE_USER;

@WebServlet(DELETE_USER)
public class DeleteUser extends HttpServlet {
    PersonInfoService personInfoService = PersonInfoService.getINSTANCE();
    AuthorisationService authorisationService = AuthorisationService.getINSTANCE();
    AuthenticationService authenticationService = AuthenticationService.getINSTANCE();
    ClientRatingService clientRatingService = ClientRatingService.getINSTANCE();
    ClientService clientService = ClientService.getINSTANCE();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        PersonInfoDto user = (PersonInfoDto) request.getSession().getAttribute("personInfo");
        Long deletePersonInfoId = user.getId();
        Long deleteClientId = clientService.findAllWithFilters
                        (new ClientFilter(1, 0, null, deletePersonInfoId, null))
                .getLast().getId();
        Long deleteClientRatingId = clientService.findAllWithFilters
                        (new ClientFilter(1, 0, null, deletePersonInfoId, null))
                .getLast().getClient_rating_id();

        authenticationService.deleteUser(deletePersonInfoId);
        authorisationService.deleteUser(deletePersonInfoId);

        clientService.delete(deleteClientId);
        clientRatingService.delete(Math.toIntExact(deleteClientRatingId));

        personInfoService.delete(deletePersonInfoId);
        response.sendRedirect(UrlPath.START_PAGE);
    }
}