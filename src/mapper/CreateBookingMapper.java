package mapper;

import dto.booking.CreateBookingDto;
import entity.Booking;

import java.time.LocalDateTime;

public class CreateBookingMapper implements Mapper<CreateBookingDto, Booking> {

    // Делаем Singleton'ом, т.к. могут быть зависимости на другие bean'ы и прочее.
    // Также он потокобезопасный, т.к. нет изменяющего состояния
    private static CreateBookingMapper INSTANCE = new CreateBookingMapper();

    private CreateBookingMapper() {
    }

    public static CreateBookingMapper getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Booking mapFrom(CreateBookingDto object) {
        return Booking.builder()
                .clientId(Long.valueOf(object.getClient_id()))
                .roomId(Long.valueOf(object.getRoom_id()))
                .dateFrom(LocalDateTime.parse(object.getDate_from()))
                .dateTo(LocalDateTime.parse(object.getDate_to()))
                .isApproved(Boolean.valueOf(object.getIs_approved()))
                .isPaid(Boolean.valueOf(object.getIs_paid()))
                .build();
    }
}
