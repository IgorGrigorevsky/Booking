package dto.employee;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class EmployeeDto {

    private final Long id;
    private final Long person_info_id;
    private final Long position_id;
    private final Long hotel_id;
}
