package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    private Long id;
    private Long clientId;
    //    private Client clientId;
    private Long roomId;
    //    private Room roomId;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Boolean isApproved;
    private Boolean isPaid;

    @Override
    public String toString() {
        return "EntityBooking{" +
               "идентификатор бронирования " + id +
               ", идентификатор клиента " + clientId +
               ", идентификатор комнаты " + roomId +
               ", дата заезда " + dateFrom +
               ", дата выезда " + dateTo +
               ", статус подтверждения " + isApproved +
               ", статус оплаты " + isPaid +
               '}';
    }
}
