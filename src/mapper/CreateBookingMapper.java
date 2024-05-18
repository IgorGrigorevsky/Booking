package mapper;

import dto.booking.CreateBookingDto;
import entity.Booking;

import java.time.LocalDate;

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
                .clientId(Long.valueOf(object.getClientId()))
                .roomId(Long.valueOf(object.getRoomId()))
                .dateFrom(LocalDate.parse(object.getDateFrom()))
                .dateTo(LocalDate.parse(object.getDateTo()))
                .isApproved(Boolean.valueOf(object.getIsApproved()))
                .isPaid(Boolean.valueOf(object.getIsPaid()))
                .build();
    }
}
