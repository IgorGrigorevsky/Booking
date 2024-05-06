package mapper;

import dto.client.CreateClientDto;
import dto.clientRating.CreateClientRatingDto;
import entity.Client;
import entity.ClientRating;

public class CreateClientRatingMapper  implements Mapper<CreateClientRatingDto, ClientRating> {

    // Делаем Singleton'ом, т.к. могут быть зависимости на другие bean'ы и прочее.
    // Также он потокобезопасный, т.к. нет изменяющего состояния
    private static CreateClientRatingMapper INSTANCE = new CreateClientRatingMapper();

    private CreateClientRatingMapper() {
    }

    public static CreateClientRatingMapper getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public ClientRating mapFrom(CreateClientRatingDto object) {
        return ClientRating.builder()
                .rating(Integer.valueOf(object.getRating()))
                .build();
    }
}