package dto;

import java.sql.Timestamp;

public record BookingFilter(int limit,
                            int offset,
                            Long id,
                            Long client_id,
                            Long room_id,
                            Timestamp date_from,
                            Timestamp date_to,
                            boolean is_approved,
                            boolean is_paid) {
}
