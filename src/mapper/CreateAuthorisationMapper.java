package mapper;

import dto.authorisation.CreateAuthorisationDto;
import entity.Authorisation;

public class CreateAuthorisationMapper implements Mapper<CreateAuthorisationDto, Authorisation> {

    // Делаем Singleton'ом, т.к. могут быть зависимости на другие bean'ы и прочее.
    // Также он потокобезопасный, т.к. нет изменяющего состояния
    private static CreateAuthorisationMapper INSTANCE = new CreateAuthorisationMapper();

    private CreateAuthorisationMapper() {
    }

    public static CreateAuthorisationMapper getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Authorisation mapFrom(CreateAuthorisationDto object) {
        return Authorisation.builder()
                .personInfoId(object.getPersonInfoId())
                .roleId(object.getRoleId())
                .isClient(object.getIsClient())
                .build();

    }
}
