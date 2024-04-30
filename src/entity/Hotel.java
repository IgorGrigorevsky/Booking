package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class Hotel {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    //    private Long starsId;
    private Long starsId;

    public Hotel(Long id, String name, String address, String phone, String email, Long starsId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.starsId = starsId;
    }

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
