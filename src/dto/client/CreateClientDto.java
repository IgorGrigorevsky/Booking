package dto.client;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateClientDto {

    private final String personInfoId;
    // автоматический стартовый уровень рейтинга
    private final String clientRatingId;
}
