package service;

import dao.DaoEmployee;
import dto.employee.EmployeeDto;
import dto.employee.EmployeeFilter;
import entity.Employee;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class EmployeeService {

    // паттерн Singleton
    private static final EmployeeService INSTANCE = new EmployeeService();

    private final DaoEmployee daoEmployee = DaoEmployee.getInstance();

    private EmployeeService() {
    }

    public static EmployeeService getINSTANCE() {
        return INSTANCE;
    }

    public EmployeeDto save(Employee employee) {
        Employee savedEmployee = daoEmployee.save(employee);
        return new EmployeeDto(savedEmployee.getId(), savedEmployee.getPersonInfoId(),
                savedEmployee.getPositionId(), savedEmployee.getHotelId());
    }

    public List<EmployeeDto> findAll() {

        // получив на выходе List -> мы преобразовываем объекты Employee в EmployeeDto
        return daoEmployee.findAll().stream()
                .map(employee -> new EmployeeDto(employee.getId(), employee.getPersonInfoId(),
                        employee.getPositionId(), employee.getHotelId()
                )).collect(toList());
    }

    public List<EmployeeDto> findById(Long id) {
        return daoEmployee.findById(id).stream()
                .map(employee -> new EmployeeDto(employee.getId(), employee.getPersonInfoId(),
                        employee.getPositionId(), employee.getHotelId()
                )).collect(toList());
    }

    public List<EmployeeDto> findAllWithFilters(EmployeeFilter filter) {
        return daoEmployee.findAllWithFilters(filter).stream()
                .map(employee -> new EmployeeDto(employee.getId(), employee.getPersonInfoId(),
                        employee.getPositionId(), employee.getHotelId()
                )).collect(toList());
    }

    public boolean delete(Long id) {
        return daoEmployee.delete(id);
    }
}
