package dto;

public record EmployeePositionFilter(int limit,
                                     int offset,
                                     Long id,
                                     String position) {
}
