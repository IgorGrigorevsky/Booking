package mapper;

import dto.personInfo.PersonInfoDto;
import entity.PersonInfo;

public class CreatePersonInfoMapperWhenLoggin implements Mapper<PersonInfo, PersonInfoDto> {

    private static CreatePersonInfoMapperWhenLoggin INSTANCE = new CreatePersonInfoMapperWhenLoggin();

    private CreatePersonInfoMapperWhenLoggin() {
    }

    public static CreatePersonInfoMapperWhenLoggin getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public PersonInfoDto mapFrom(PersonInfo object) {
        return PersonInfoDto.builder()
                .id(object.getId())
                .full_name(object.getFullName())
                .phone_number(object.getPhoneNumber())
                .email(object.getEmail())
                .build();
    }
}
