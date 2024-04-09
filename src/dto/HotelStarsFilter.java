package dto;

public record HotelStarsFilter(int limit,
                               int offset,
                               Long id,
                               Integer stars) {
}
