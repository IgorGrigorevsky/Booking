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
public class Hotel {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    //    private Long starsId;
    private Long starsId;

    @Override
    public String toString() {
        return "EntityHotel " + "id = " + id + ", название - " + name +
               ", адрес - " + address +
               ", телефон - " + phone +
               ", email - " + email +
               ", stars_id - " + starsId +
               "}\n";
    }
}
