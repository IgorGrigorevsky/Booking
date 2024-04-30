package dto.personInfo;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreatePersonInfoDto {

    private final String fullName;
    private final String phoneNumber;
    private final String email;
}
