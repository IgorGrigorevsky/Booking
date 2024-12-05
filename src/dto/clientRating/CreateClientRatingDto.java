package dto.clientRating;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateClientRatingDto {

    private final String rating;
}
