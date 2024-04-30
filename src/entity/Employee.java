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
public class Employee {

    private Long id;
//    private Long person_info_id;
    private Long personInfoId;
//    private Long positionId;
    private Long positionId;
//    private Long hotelId;
    private Long hotelId;

    @Override
    public String toString() {
        return "EntityEmployee{" +
               "id=" + id +
               ", person_info_id=" + personInfoId +
               ", position_id=" + positionId +
               ", hotel_id=" + hotelId +
               '}';
    }
}
