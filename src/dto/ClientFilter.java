package dto;

public record ClientFilter(int limit,
                           int offset,
                           Long id,
                           Long person_info_id,
                           Long client_rating_id) {
}
