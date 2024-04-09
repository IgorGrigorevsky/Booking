package dto;

public record ClientFilter(int limit,
                           int offset,
                           Long id,
                           Long personal_info_id,
                           Long client_rating_id) {
}
