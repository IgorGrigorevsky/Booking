package dto.booking;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateBookingDto {

    String clientId;
    String roomId;
    String dateFrom;
    String dateTo;
    String isApproved;
    String isPaid;
}