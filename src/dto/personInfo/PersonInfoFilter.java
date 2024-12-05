package dto.personInfo;

public record PersonInfoFilter(int limit,
                               int offset,
                               Long id,
                               String full_name,
                               String phone_number,
                               String email) {
}
