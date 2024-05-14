package dto.authorisation;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateAuthorisationDto {

    Long personInfoId;
    String roleId;
    Boolean isClient;
}
