package dto.booking;

import lombok.Builder;
import lombok.Value;

import java.sql.Timestamp;

@Value
@Builder
public class CreateBookingDto {

    String client_id;
    String room_id;
    String date_from;
    String date_to;
    String is_approved;
    String is_paid;
}