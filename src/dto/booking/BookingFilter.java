package dto.booking;

import java.sql.Timestamp;

public record BookingFilter(int limit,
                            int offset,
                            Long id,
                            Long client_id,
                            Long room_id,
                            Timestamp date_from,
                            Timestamp date_to,
                            Boolean is_approved,
                            Boolean is_paid) {
}
