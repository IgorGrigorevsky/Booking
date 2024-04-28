package dto.hotelStars;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class HotelStarsDto {

    private final Long id;
    private final Integer stars;
}
