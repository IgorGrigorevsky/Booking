package mapper;

import dto.hotel.CreateHotelDto;
import entity.Hotel;

public class CreateHotelMapper implements Mapper<CreateHotelDto, Hotel> {

    // Делаем Singleton'ом, т.к. могут быть зависимости на другие bean'ы и прочее.
    // Также он потокобезопасный, т.к. нет изменяющего состояния
    private static CreateHotelMapper INSTANCE = new CreateHotelMapper();

    private CreateHotelMapper() {
    }

    public static CreateHotelMapper getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Hotel mapFrom(CreateHotelDto object) {
        return Hotel.builder()
                .name(object.getName())
                .address(object.getAddress())
                .phone(object.getPhone())
                .email(object.getEmail())
                .starsId(Long.valueOf(object.getStarsId()))
                .build();

    }
}
