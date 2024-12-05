package dto.room;

public record RoomFilter(int limit,
                         int offset,
                         Long id,
                         Long hotel_id,
                         Integer beds_count,
                         Integer floor,
                         Boolean included_breakfast,
                         Long class_id,
                         Integer price) {
}
