package dto.hotel;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateHotelDto {

    String name;
    String address;
    String phone;
    String email;
    String starsId;
}
