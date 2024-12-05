package dao;

import dto.roomClass.RoomClassFilter;
import entity.RoomClass;
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

public class DaoRoomClass implements Dao<Long, RoomClass> {


    // простой вариант pattern'а Singletone
    private final static DaoRoomClass INSTANCE = new DaoRoomClass();

    private DaoRoomClass() {
    }

    // операция CREATE одной entity (строки) в БД
    private static final String SAVE_SQL = """
            INSERT INTO room_class (class)
            VALUES (?)
            """;

    @Override
    public RoomClass save(RoomClass roomClass) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, roomClass.getRoomClass());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                roomClass.setId(generatedKeys.getLong("id"));
            }
        } catch (Exception throwable) {
            throw new DaoException(throwable);
        }
        return roomClass;
    }

    // строка-константа для поиска всех значений из таблицы
    private static final String FIND_ALL_SQL = """
            SELECT id, class
            FROM room_class
            """;

    // метод будет возвращать все значения таблицы (список всех Entity)
    // обычно такой метод используется только для справочных таблиц
    @Override
    public List<RoomClass> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<RoomClass> infoEntityList = new ArrayList<>();
            while (resultSet.next()) {
                infoEntityList.add(buildEntityRoomClass(resultSet));
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
            --SELECT (id, class)
            FROM room_class
            WHERE id = ?
            """;

    // так как возвращаемый EntityRoom может быть NULL, мы возвращаем Optional<>
    @Override
    public Optional<RoomClass> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // создаем объект и сразу же его возвращаем, оборачивая в Optional <>
            RoomClass roomClass = null;

            // по id мы можем получить: или одну сущность - тогда мы начнем создавать наш InfoEntity
            // или ни одной - тогда мы возвращаем пустой Optional <>
            if (resultSet.next()) {
                roomClass = buildEntityRoomClass(resultSet);
            }
            return Optional.ofNullable(roomClass);
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
    public List<RoomClass> findAllWithFilters(RoomClassFilter filter) {

        // т.к. разные варианты фильтрации, то создаём коллекцию объектов для фильтрации (наших знаков "?")
        List<Object> parametrList = new ArrayList<>();

        // для фильтрации WHERE
        List<String> whereSqlList = new ArrayList<>();

        // в случае параметров, которые могут быть, а могут и не быть, мы должны их проверить на "not null"
        if (filter.id() != null) {
            whereSqlList.add(" id = ? ");
            parametrList.add(filter.id());
        }
        if (filter.roomClass() != null) {
            whereSqlList.add(" class LIKE ? ");
            parametrList.add("%" + filter.roomClass() + "%");
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
            List<RoomClass> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(buildEntityRoomClass(
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
            UPDATE room_class
            SET class = ?
            WHERE id = ?
            """;

    @Override
    public void update(RoomClass roomClass) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, roomClass.getRoomClass());
            preparedStatement.setLong(2, roomClass.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    // операция DELETE одной entity (строки) из БД
    private static final String DELETE_SQL = """
            DELETE FROM room_class
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

    // метод для создания сущности EntityRoomClass
    private static RoomClass buildEntityRoomClass(ResultSet resultSet) throws SQLException {
        return new RoomClass(
                resultSet.getLong("id"),
                resultSet.getString("class"));
    }

    public static DaoRoomClass getInstance() {
        return INSTANCE;
    }
}

