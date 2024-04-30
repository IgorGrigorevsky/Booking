package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Boolean isApproved;
    private Boolean isPaid;

    @Override
    public String toString() {
        return "EntityBooking{" +
               "id=" + id +
               ", client_id=" + clientId +
               ", room_id=" + roomId +
               ", date_from=" + dateFrom +
               ", date_to=" + dateTo +
               ", is_approved=" + isApproved +
               ", is_paid=" + isPaid +
               '}';
    }
}
