package dto;

public record HotelFilter(int limit,
                          int offset,
                          Long id,
                          String name,
                          String address,
                          String phone,
                          String email,
                          Long stars_id) {
}
