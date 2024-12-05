package dto.client;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class ClientDto {

    private final Long id;
    private final Long person_info_id;
    private final Long client_rating_id;
}
