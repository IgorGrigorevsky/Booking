package mapper;

import dto.client.CreateClientDto;
import entity.Client;

public class CreateClientMapper implements Mapper<CreateClientDto, Client> {

    // Делаем Singleton'ом, т.к. могут быть зависимости на другие bean'ы и прочее.
    // Также он потокобезопасный, т.к. нет изменяющего состояния
    private static CreateClientMapper INSTANCE = new CreateClientMapper();

    private CreateClientMapper() {
    }

    public static CreateClientMapper getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Client mapFrom(CreateClientDto object) {
        return Client.builder()
                .personalInfoId(Long.valueOf(object.getPersonInfoId()))
                .clientRatingId(Long.valueOf(object.getClientRatingId()))
                .build();
    }
}
