package dto;


import lombok.Value;
import lombok.Builder;

@Value
@Builder
public class UserDto {
    Long id;
    String mail;
}
