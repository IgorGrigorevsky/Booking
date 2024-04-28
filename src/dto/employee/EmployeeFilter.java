package dto.employee;

public record EmployeeFilter(int limit,
                             int offset,
                             Long id,
                             Long person_info_id,
                             Long position_id,
                             Long hotel_id) {
}
