package mapper;

import dto.personInfo.CreatePersonInfoDto;
import entity.PersonInfo;

public class CreatePersonInfoMapper implements Mapper<CreatePersonInfoDto, PersonInfo> {

    private static CreatePersonInfoMapper INSTANCE = new CreatePersonInfoMapper();

    private CreatePersonInfoMapper() {
    }

    public static CreatePersonInfoMapper getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public PersonInfo mapFrom(CreatePersonInfoDto object) {
        return PersonInfo.builder()
                .fullName(object.getFullName())
                .phoneNumber(object.getPhoneNumber())
                .email(object.getEmail())
                .build();
    }
}
