package dto;

public record ClientRatingFilter(int limit,
                                 int offset,
                                 Long id,
                                 Integer rating) {
}
