package dao;

import dto.personInfo.PersonInfoFilter;
import entity.PersonInfo;
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

public class DaoPersonInfo implements Dao<Long, PersonInfo> {

    // создаем Singletone
    private static final DaoPersonInfo INSTANCE = new DaoPersonInfo();

    // ограничиваем создание экземпляра извне
    private DaoPersonInfo() {
    }

    private static final String REGEX_FULL_NAME = "[А-Я]{1}[а-я]{1,}\\s[А-Я]{1}[а-я]{1,}";
    private static final String REGEX_PHONE_NUMBER = "[+78]?[\\d]{9,12}";
    private static final String REGEX_EMAIL = "([\\w.]+)@[a-zA-Z]{2,10}\\.[a-z]{2,6}";

    // операция CREATE одной entity (строки) в БД
    private static final String SAVE_SQL = """
            INSERT INTO person_info (full_name, phone_number, email)
            VALUES (?,?,?)
                        
            """;

    @Override
    public PersonInfo save(PersonInfo personInfo) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, personInfo.getFullName());
            preparedStatement.setString(2, personInfo.getPhoneNumber());
            preparedStatement.setString(3, personInfo.getEmail());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                personInfo.setId(generatedKeys.getLong("id"));
            }
        } catch (Exception throwable) {
            throw new DaoException(throwable);
        }
        return personInfo;
    }

    // строка-константа для поиска всех значений из таблицы
    private static final String FIND_ALL_SQL = """
            SELECT *
            --(id, full_name, phone_number, email)
            FROM person_info
            """;

    @Override
    public List<PersonInfo> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<PersonInfo> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(buildEntityPersonInfo(resultSet));
            }
            return list;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    // строка-константа для операции SELECT (в методе UPDATE нам необходимо добавить значение
    // для Id - который мы получаем с помощью метода SELECT)
    private static final String FIND_BY_ID_SQL = """
            SELECT *
            --SELECT (id, full_name, phone_number, email)
            FROM person_info
            WHERE id = ?
            """;

    // так как возвращаемый EntityRoom может быть NULL, мы возвращаем Optional<>
    @Override
    public Optional<PersonInfo> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // создаем объект и сразу же его возвращаем, оборачивая в Optional <>
            PersonInfo personInfo = null;

            // по id мы можем получить: или одну сущность - тогда мы начнем создавать наш InfoEntity
            // или ни одной - тогда мы возвращаем пустой Optional <>
            if (resultSet.next()) {
                personInfo = buildEntityPersonInfo(resultSet);
            }
            return Optional.ofNullable(personInfo);
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    // строка-константа для операции Authentication по почте и паролю из другой таблицы
    private static final String FIND_BY_PASSWORD_AND_EMAIL = """
            SELECT *
            FROM person_info
            JOIN public.authentication as a on person_info.id = a.person_info_id
            WHERE password LIKE ?
              AND email LIKE ?
            """;

    // так как возвращаемый Entity может быть NULL, мы возвращаем Optional<>
    public Optional<PersonInfo> findByPasswordAndEmail(String email, String password) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PASSWORD_AND_EMAIL)) {
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            // создаем объект и сразу же его возвращаем, оборачивая в Optional <>
            PersonInfo personInfo = null;

            // по id мы можем получить: или одну сущность
            // или ни одной - тогда мы возвращаем пустой Optional <>
            if (resultSet.next()) {
                personInfo = buildEntityPersonInfo(resultSet);
            }
            return Optional.ofNullable(personInfo);
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
    public List<PersonInfo> findAllWithFilters(PersonInfoFilter filter) {

        // т.к. разные варианты фильтрации, то создаём коллекцию объектов для фильтрации (наших знаков "?")
        List<Object> parametrList = new ArrayList<>();

        // для фильтрации WHERE
        List<String> whereSqlList = new ArrayList<>();

        // в случае параметров, которые могут быть, а могут и не быть, мы должны их проверить на "not null"
        if (filter.id() != null) {
            whereSqlList.add(" id = ? ");
            parametrList.add(filter.id());
        }
        if (filter.full_name() != null) {
            whereSqlList.add(" full_name ILIKE ? ");
            parametrList.add("%" + filter.full_name() + "%");
        }
        if (filter.phone_number() != null) {
            whereSqlList.add(" phone_number ILIKE ? ");
            parametrList.add("%" + filter.phone_number() + "%");
        }
        if (filter.email() != null) {
            whereSqlList.add(" email ILIKE ? ");
            parametrList.add("%" + filter.email() + "%");
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
            List<PersonInfo> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(buildEntityPersonInfo(
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
            UPDATE person_info
            SET  full_name = ?,
                 phone_number = ?,
                 email = ?
            WHERE id = ?
            """;

    @Override
    public void update(PersonInfo personInfo) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, personInfo.getFullName());
            preparedStatement.setString(2, personInfo.getPhoneNumber());
            preparedStatement.setString(3, personInfo.getEmail());
            preparedStatement.setLong(4, personInfo.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    // операция DELETE одной entity (строки) из БД
    private static final String DELETE_SQL = """
            DELETE FROM person_info
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

    // метод для создания сущности EntityPersonInfo
    private static PersonInfo buildEntityPersonInfo(ResultSet resultSet) throws SQLException {
        return new PersonInfo(
                resultSet.getLong("id"),
                resultSet.getString("full_name"),
                resultSet.getString("phone_number"),
                resultSet.getString("email"));
    }

    public static boolean checkFullName(String fullName) {
        return fullName.matches(REGEX_FULL_NAME);
    }

    public static boolean checkPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(REGEX_PHONE_NUMBER);
    }

    public static boolean checkEmail(String email) {
        return email.matches(REGEX_EMAIL);
    }

    public static DaoPersonInfo getInstance() {
        return INSTANCE;
    }
}
