package dto.personInfo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PersonInfoDto {

    private final Long id;
    private final String full_name;
    private final String phone_number;
    private final String email;
}
