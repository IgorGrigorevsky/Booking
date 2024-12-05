package dto.booking;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class BookingDto {

    private final Long id;
    private final Long client_id;
    private final Long room_id;
    private final LocalDate date_from;
    private final LocalDate date_to;
    private final Boolean is_approved;
    private final Boolean is_paid;

    @Override
    public String toString() {
        return "Заявка на бронирование{" +
               "идентификатор бронирования " + id +
               ", идентификатор клиента " + client_id +
               ", идентификатор комнаты " + room_id +
               ", дата заезда " + date_from +
               ", дата выезда " + date_to +
               ", статус подтверждения " + is_approved +
               ", статус оплаты " + is_paid +
               '}';
    }
}
