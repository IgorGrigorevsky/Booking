package dto.clientRating;

public record ClientRatingFilter(int limit,
                                 int offset,
                                 Integer id,
                                 Integer rating) {
}
