package dto;

public record RoomClassFilter(int limit,
                              int offset,
                              Long id,
                              String roomClass) {
}
