package dto.booking;

import java.time.LocalDate;

public record BookingFilter(int limit,
                            int offset,
                            Long id,
                            Long client_id,
                            Long room_id,
                            LocalDate date_from,
                            LocalDate date_to,
                            Boolean is_approved,
                            Boolean is_paid) {
}
