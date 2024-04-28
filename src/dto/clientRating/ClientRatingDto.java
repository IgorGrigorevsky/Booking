package dto.clientRating;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class ClientRatingDto {

    private final Integer id;
    private final Integer rating;
}
