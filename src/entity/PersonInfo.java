package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonInfo {

    private Long id;
    private String fullName;
    private String phoneNumber;
    private String email;

    @Override
    public String toString() {
        return "EntityPersonInfo{" +
               "id=" + id +
               ", full_name='" + fullName + '\'' +
               ", phone='" + phoneNumber + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
