package mapper;

import dto.authentication.CreateAuthenticationDto;
import entity.Authentication;

public class CreateAuthenticationMapper implements Mapper<CreateAuthenticationDto, Authentication> {

    // Делаем Singleton'ом, т.к. могут быть зависимости на другие bean'ы и прочее.
    // Также он потокобезопасный, т.к. нет изменяющего состояния
    private static CreateAuthenticationMapper INSTANCE = new CreateAuthenticationMapper();

    private CreateAuthenticationMapper() {
    }

    public static CreateAuthenticationMapper getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Authentication mapFrom(CreateAuthenticationDto object) {
        return Authentication.builder()
                .personId(object.getPersonInfoId())
                .password(object.getPassword())
                .build();

    }
}
