package runner;

import dao.DaoEmployee;
import dto.EmployeeFilter;
import entity.Employee;

import java.util.List;
import java.util.Optional;

public class DaoEmployeeRunner {
    public static void main(String[] args) {

        // CREATE
        saveEmployee(1L, 1L, 1L);

        // READ ALL
        selectAll();
        selectAllWithFilter(5, 0, null, 5L, null, 3L);

        // todo UPDATE - работает, при условии, что иные колонки не NULL
        updateEmployeePersonInfoId(19L, 5L);
        updateEmployeePositionId(19L,2L);
        updateEmployeeHotelId(19L,2L);

        // DELETE
        deleteEmployee(19L);
    }

    private static void saveEmployee(long personInfoId, long positionID, long hotelId) {
        DaoEmployee daoEmployee = DaoEmployee.getInstance();
        Employee employee = new Employee();

        employee.setPersonInfoId(personInfoId);
        employee.setPositionId(positionID);
        employee.setHotelId(hotelId);

        Employee savedValue = daoEmployee.save(employee);
        System.out.println("Сохраненная запись: \n" + savedValue);
    }

    private static void selectAll() {
        List<Employee> all = DaoEmployee.getInstance().findAll();
        System.out.println(all);
    }

    private static void selectAllWithFilter(
            int limit, int offset, Long id, Long personInfoId, Long positionId, Long hotelId) {
        EmployeeFilter filter = new EmployeeFilter(limit, offset, id, personInfoId, positionId, hotelId);
        List<Employee> allWithFilters = DaoEmployee.getInstance().findAllWithFilters(filter);
        System.out.println(allWithFilters);
    }

    private static void updateEmployeePersonInfoId(Long id, Long newPersonInfoId) {
        DaoEmployee instance = DaoEmployee.getInstance();
        Optional<Employee> maybeEntityEmployee = instance.findById(id);
        System.out.println("имеющийся person_info_id: \n" + maybeEntityEmployee);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntityEmployee.ifPresent(employee -> {
            employee.setPersonInfoId(newPersonInfoId);
            instance.update(employee);
        });
        System.out.println("измененный person_info_id: \n" + maybeEntityEmployee);
    }

    private static void updateEmployeePositionId(Long id, Long newPersonPositionId) {
        DaoEmployee instance = DaoEmployee.getInstance();
        Optional<Employee> maybeEntity = instance.findById(id);
        System.out.println("имеющийся person_position_id: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(employee -> {
            employee.setPositionId(newPersonPositionId);
            instance.update(employee);
        });
        System.out.println("измененный person_position_id: \n" + maybeEntity);
    }

    private static void updateEmployeeHotelId(Long id, Long newPersonHotelId) {
        DaoEmployee instance = DaoEmployee.getInstance();
        Optional<Employee> maybeEntityEmployee = instance.findById(id);
        System.out.println("имеющийся person_hotel_id: \n" + maybeEntityEmployee);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntityEmployee.ifPresent(employee -> {
            employee.setHotelId(newPersonHotelId);
            instance.update(employee);
        });
        System.out.println("измененный person_hotel_id: \n" + maybeEntityEmployee);
    }

    private static void deleteEmployee(long id) {
        DaoEmployee daoEmployee = DaoEmployee.getInstance();
        boolean result = daoEmployee.delete(id);
        System.out.println("Получилось удалить запись? "+ result);
    }
}
