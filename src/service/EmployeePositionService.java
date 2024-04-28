package service;

import dao.DaoEmployeePosition;
import dto.employeePosition.EmployeePositionDto;
import dto.employeePosition.EmployeePositionFilter;
import entity.EmployeePosition;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class EmployeePositionService {

    // паттерн Singleton
    private static final EmployeePositionService INSTANCE = new EmployeePositionService();

    private final DaoEmployeePosition daoEmployeePosition = DaoEmployeePosition.getInstance();

    private EmployeePositionService() {
    }

    public static EmployeePositionService getINSTANCE() {
        return INSTANCE;
    }

    public EmployeePositionDto save(EmployeePosition employeePosition) {
        EmployeePosition savedEmployeePosition = daoEmployeePosition.save(employeePosition);
        return new EmployeePositionDto(savedEmployeePosition.getId(), savedEmployeePosition.getPosition());
    }

    public List<EmployeePositionDto> findAll() {

        // получив на выходе List -> мы преобразовываем объекты EmployeePosition в EmployeePositionDto
        return daoEmployeePosition.findAll().stream()
                .map(employeePosition -> new EmployeePositionDto(employeePosition.getId(),
                        employeePosition.getPosition()
                )).collect(toList());
    }

    public List<EmployeePositionDto> findById(Long id) {
        return daoEmployeePosition.findById(id).stream()
                .map(employeePosition -> new EmployeePositionDto(employeePosition.getId(),
                        employeePosition.getPosition()
                )).collect(toList());
    }

    public List<EmployeePositionDto> findAllWithFilters(EmployeePositionFilter filter) {
        return daoEmployeePosition.findAllWithFilters(filter).stream()
                .map(employeePosition -> new EmployeePositionDto(employeePosition.getId(),
                        employeePosition.getPosition()
                )).collect(toList());
    }

    public boolean delete(Long id) {
        return daoEmployeePosition.delete(id);
    }
}