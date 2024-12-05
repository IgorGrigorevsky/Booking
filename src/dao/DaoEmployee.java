package dao;

import dto.employee.EmployeeFilter;
import entity.Employee;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class DaoEmployee implements Dao<Long, Employee> {

    // простой вариант pattern'а Singletone
    private final static DaoEmployee INSTANCE = new DaoEmployee();

    private DaoEmployee() {
    }

    // операция CREATE одной entity (строки) в БД
    private static final String SAVE_SQL = """
            INSERT INTO employee (person_info_id, position_id, hotel_id)
            VALUES (?,?,?)
            """;

    @Override
    public Employee save(Employee employee) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, employee.getPersonInfoId());
            preparedStatement.setLong(2, employee.getPositionId());
            preparedStatement.setLong(3, employee.getHotelId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                employee.setId(generatedKeys.getLong("id"));
            }
        } catch (Exception throwable) {
            throw new DaoException(throwable);
        }
        return employee;
    }

    // строка-константа для поиска всех значений из таблицы
    private static final String FIND_ALL_SQL = """
            SELECT id, person_info_id, position_id, hotel_id
            FROM employee
            """;

    // метод будет возвращать все значения таблицы (список всех Entity)
    // обычно такой метод используется только для справочных таблиц
    @Override
    public List<Employee> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Employee> infoEntityList = new ArrayList<>();
            while (resultSet.next()) {
                infoEntityList.add(buildEntityEmployee(resultSet));
            }
            return infoEntityList;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    // строка-константа для операции SELECT (в методе UPDATE нам необходимо добавить значение
    // для Id - который мы получаем с помощью метода SELECT)
    private static final String FIND_BY_ID_SQL = """
            SELECT *
            --SELECT (id, person_info_id, position_id, hotel_id)
            FROM employee
            WHERE id = ?
            """;

    // так как возвращаемый EntityRoom может быть NULL, мы возвращаем Optional<>
    @Override
    public Optional<Employee> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // создаем объект и сразу же его возвращаем, оборачивая в Optional <>
            Employee employee = null;

            // по id мы можем получить: или одну сущность - тогда мы начнем создавать наш InfoEntity
            // или ни одной - тогда мы возвращаем пустой Optional <>
            if (resultSet.next()) {
                employee = buildEntityEmployee(resultSet);
            }
            return Optional.ofNullable(employee);
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    // в параметры метода мы добавляем фильтрацию, для получения конкретной выборки
    // и используем ограничители LIMIT'а и OFFSET'а
    // --> нам нужно использовать BATCH-SELECT

    // в параметры передаем объект фильтрации, класс которого мы создадим в пакете "dto"

    // передаваемый SQL-запрос динамический (по умолчанию он будет выглядеть как "findAll")
    // далее, на основании фильтра достраиваем условие WHERE (если там есть какие-то поля) и добавляем
    // LIMIT и OFFSET в конце
    // этот SQL-запрос будем формировать во время выполнения нашего метода .findAll
    public List<Employee> findAllWithFilters(EmployeeFilter filter) {

        // т.к. разные варианты фильтрации, то создаём коллекцию объектов для фильтрации (наших знаков "?")
        List<Object> parametrList = new ArrayList<>();

        // для фильтрации WHERE
        List<String> whereSqlList = new ArrayList<>();

        // в случае параметров, которые могут быть, а могут и не быть, мы должны их проверить на "not null"
        if (filter.id() != null) {
            whereSqlList.add(" id = ? ");
            parametrList.add(filter.id());
        }
        if (filter.person_info_id() != null) {
            whereSqlList.add(" person_info_id = ? ");
            parametrList.add(filter.person_info_id());
        }
        if (filter.position_id() != null) {
            whereSqlList.add(" position_id = ? ");
            parametrList.add(filter.position_id());
        }
        if (filter.hotel_id() != null) {
            whereSqlList.add(" hotel_id = ? ");
            parametrList.add(filter.hotel_id());
        }
        parametrList.add(filter.limit());
        parametrList.add(filter.offset());

        // пробегаемся по стриму и коллектим параметры с помощью метода .joing()
        // обязательно добавляем статический импорт для коллекторов
        String where = whereSqlList.stream()
                .collect(joining(" AND ", " WHERE ", " LIMIT ? OFFSET ? "));

        String sql = whereSqlList.isEmpty() ? FIND_ALL_SQL + " LIMIT ? OFFSET ? " : FIND_ALL_SQL + where;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // проходимся по параметрам циклом и устанавливаем их в объект prepareStatement вместо "?"
            // если в фильтре не будет ни одного параметра,
            // то нужно добавить пустую строку вместо where (иначе будет ошибка)
            for (int i = 0; i < parametrList.size(); i++) {
                // т.к. установка параметров в prepareStatement начинается с 1, а не с 0, то мы
                // прибавляем единицу, когда устанавливаем какой-то объект
                // т.к. мы не хотим заморачиваться с определенным типом данных у нашего параметра -->
                // мы используем setObject, который универсальный
                preparedStatement.setObject(i + 1, parametrList.get(i));
            }

            ResultSet resultSet = preparedStatement.executeQuery();

            // создаем результирующий набор объектов
            List<Employee> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(buildEntityEmployee(
                        resultSet));
            }
            return list;
        } catch (
                SQLException
                        throwable) {
            throw new DaoException(throwable);
        }
    }

    // операция UPDATE одной entity (строки) из БД
    private static final String UPDATE_SQL = """
            UPDATE employee
            SET  person_info_id = ?,
                 position_id = ?,
                 hotel_id = ?
            WHERE id = ?
            """;

    @Override
    public void update(Employee employee) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1, employee.getPersonInfoId());
            preparedStatement.setLong(2, employee.getPositionId());
            preparedStatement.setLong(3, employee.getHotelId());
            preparedStatement.setLong(4, employee.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    // операция DELETE одной entity (строки) из БД
    private static final String DELETE_SQL = """
            DELETE FROM employee
            WHERE id = ?
            """;

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);

            // если вернется отрицательное число, удалить строку не получилось
            return preparedStatement.executeUpdate() > 0;
        } catch (Exception throwable) {
            throw new DaoException(throwable);
        }
    }

    // метод для создания сущности EntityEmployee
    private static Employee buildEntityEmployee(ResultSet resultSet) throws SQLException {
        return new Employee(
                resultSet.getLong("id"),
                resultSet.getLong("person_info_id"),
                resultSet.getLong("position_id"),
                resultSet.getLong("hotel_id")
        );
    }

    public static DaoEmployee getInstance() {
        return INSTANCE;
    }
}

