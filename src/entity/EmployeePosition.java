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
public class EmployeePosition {
    private Long id;
    private String position;

    @Override
    public String toString() {
        return "EntityEmployeePosition{" +
               "id=" + id +
               ", position='" + position + '\'' +
               '}';
    }
}
