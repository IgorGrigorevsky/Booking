package dto.hotelStars;

public record HotelStarsFilter(int limit,
                               int offset,
                               Long id,
                               Integer stars) {
}
