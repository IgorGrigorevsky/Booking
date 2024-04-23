package runner;

import dao.DaoEmployeePosition;
import dto.EmployeePositionFilter;
import entity.EmployeePosition;

import java.util.List;
import java.util.Optional;

public class DaoEmployeePositionRunner {
    public static void main(String[] args) {


        // CREATE
        saveEmployeePosition("уборщик");

        // READ ALL
        selectAll();
        selectAllWithFilter(5, 0, 1L, "админ");

        // UPDATE
        updateEmployeePosition(3L, "слесарь");

        // DELETE
        deleteEmployeePosition(3L);
    }

    private static void saveEmployeePosition(String newPosition) {
        DaoEmployeePosition daoEmployeePosition = DaoEmployeePosition.getInstance();
        EmployeePosition employeePosition = new EmployeePosition();

        employeePosition.setPosition(newPosition);

        EmployeePosition savedValue = daoEmployeePosition.save(employeePosition);
        System.out.println("Сохраненная запись: \n" + savedValue);
    }

    private static void selectAll() {
        List<EmployeePosition> all = DaoEmployeePosition.getInstance().findAll();
        System.out.println(all);
    }

    private static void selectAllWithFilter(int limit, int offset, Long id, String position) {
        EmployeePositionFilter filter = new EmployeePositionFilter(limit, offset, id, position);
        List<EmployeePosition> allWithFilters = DaoEmployeePosition.getInstance().findAllWithFilters(filter);
        System.out.println(allWithFilters);
    }

    private static void updateEmployeePosition(Long id, String newPosition) {
        DaoEmployeePosition instance = DaoEmployeePosition.getInstance();
        Optional<EmployeePosition> maybeEntity = instance.findById(id);
        System.out.println("имеющаяся position: \n" + maybeEntity);

        // так как у нас Optional, сначала проверяем есть ли объект
        maybeEntity.ifPresent(employeePosition -> {
            employeePosition.setPosition(newPosition);
            instance.update(employeePosition);
        });
        System.out.println("измененная position: \n" + maybeEntity);
    }

    private static void deleteEmployeePosition(long id) {
        DaoEmployeePosition daoEmployeePosition = DaoEmployeePosition.getInstance();
        boolean result = daoEmployeePosition.delete(id);
        System.out.println("Получилось удалить запись? " + result);
    }
}

