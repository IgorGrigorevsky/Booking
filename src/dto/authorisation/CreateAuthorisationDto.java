package dto.authorisation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class CreateAuthorisationDto {

    Long personInfoId;
    String roleId;
    Boolean isClient;
}
