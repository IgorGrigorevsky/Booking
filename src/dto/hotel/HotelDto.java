package dto.hotel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class HotelDto {

    private final Long id;
    private final String name;
    private final String address;
    private final String phone;
    private final String email;
    private final Long stars_id;
}
