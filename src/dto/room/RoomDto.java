package dto.room;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class RoomDto {

    private final Long id;
    private final Long hotel_id;
    private final Integer beds_count;
    private final Integer floor;
    private final Boolean included_breakfast;
    private final Long class_id;
    private final Integer price;
}
