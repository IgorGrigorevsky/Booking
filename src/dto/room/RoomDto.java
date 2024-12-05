package dto.room;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
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

    @Override
    public String toString() {
        return "Номер комнаты " + id +
               " | идентификатор отеля: " + hotel_id +
               " | количество кроватей: " + beds_count +
               " | этаж: " + floor +
               " | наличие завтрака: " + included_breakfast +
               " | класс комнаты: " + class_id +
               " | цена: " + price +
               " руб.";
    }
}
