package dto.booking;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
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
}
