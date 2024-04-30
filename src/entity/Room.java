package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    private Long id;
//    private Long hotelId;
    private Long hotelId;
    private Integer bedsCount;
    private Integer floor;
    private Boolean includedBreakfast;
//    private Long classId;
    private Long classId;
    private Integer price;

    @Override
    public String toString() {
        return "Номер отеля: " +
               "id : " + id +
               ", id отеля номера " + hotelId +
               ", количество кроватей: " + bedsCount +
               ", этаж: " + floor +
               ", завтрак: " + includedBreakfast +
               ", id класса комнаты:  " + classId +
               ", цена :" + price +
               '}';
    }
}
