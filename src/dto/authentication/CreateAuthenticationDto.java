package dto.authentication;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateAuthenticationDto {

    Long personInfoId;
    String password;
}
