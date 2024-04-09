package dao;

import dto.RoomFilter;
import entity.EntityRoom;
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

public class DaoRoom {

    // простой вариант pattern'а Singletone
    private static DaoRoom INSTANCE = new DaoRoom();

    private DaoRoom() {
    }

    // операция DELETE одной entity (строки) из БД
    private static final String DELETE_SQL = """
            DELETE FROM room
            WHERE id = ?
            """;

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


    // операция CREATE одной entity (строки) в БД
    private static final String SAVE_SQL = """
            INSERT INTO room (hotel_id, beds_count, floor, included_breakfast, class_id, price)
            VALUES (?,?,?,?,?,?)
            """;

    public EntityRoom save(EntityRoom entityRoom) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, entityRoom.getHotel_id());
            preparedStatement.setInt(2, entityRoom.getBeds_count());
            preparedStatement.setInt(3, entityRoom.getFloor());
            preparedStatement.setBoolean(4, entityRoom.isIncluded_breakfast());
            preparedStatement.setLong(5, entityRoom.getClass_id());
            preparedStatement.setInt(6, entityRoom.getPrice());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entityRoom.setId(generatedKeys.getLong("id"));
            }
        } catch (Exception throwable) {
            throw new DaoException(throwable);
        }
        return entityRoom;
    }


    // операция UPDATE одной entity (строки) из БД
    private static final String UPDATE_SQL = """
            UPDATE room
            SET  hotel_id = ?,
                 beds_count = ?,
                 floor = ?,
                 included_breakfast = ?,
                 class_id = ?,
                 price = ?
            WHERE id = ?
            """;

    public void update(EntityRoom room) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1, room.getHotel_id());
            preparedStatement.setInt(2, room.getBeds_count());
            preparedStatement.setInt(3, room.getFloor());
            preparedStatement.setBoolean(4, room.isIncluded_breakfast());
            preparedStatement.setLong(5, room.getClass_id());
            preparedStatement.setInt(6, room.getPrice());
            preparedStatement.setLong(7, room.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    // строка-константа для операции SELECT (в методе UPDATE нам необходимо добавить значение
    // для Id - который мы получаем с помощью метода SELECT)
    private static final String FIND_BY_ID_SQL = """
            SELECT *
            --SELECT (id, hotel_id, beds_count, floor, included_breakfast, class_id, price)
            FROM room
            WHERE id = ?
            """;

    // так как возвращаемый EntityRoom может быть NULL, мы возвращаем Optional<>
    public Optional<EntityRoom> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // создаем объект и сразу же его возвращаем, оборачивая в Optional <>
            EntityRoom room = null;

            // по id мы можем получить: или одну сущность - тогда мы начнем создавать наш InfoEntity
            // или ни одной - тогда мы возвращаем пустой Optional <>
            if (resultSet.next()) {
                room = buildEntityRoom(resultSet);
            }
            return Optional.ofNullable(room);
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }


    // строка-константа для поиска всех значений из таблицы
    private static final String FIND_ALL_SQL = """
            SELECT id, hotel_id, beds_count, floor, included_breakfast, class_id, price
            FROM room
            """;

    // метод будет возвращать все значения таблицы (список всех InfoEntity)
    // обычно такой метод используется только для справочных таблиц
    public List<EntityRoom> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<EntityRoom> infoEntityList = new ArrayList<>();
            while (resultSet.next()) {
                infoEntityList.add(buildEntityRoom(resultSet));
            }
            return infoEntityList;
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
    public List<EntityRoom> findAllWithFilters(RoomFilter filter) {

        // т.к. разные варианты фильтрации, то создаём коллекцию объектов для фильтрации (наших знаков "?")
        List<Object> parametrList = new ArrayList<>();

        // для фильтрации WHERE
        List<String> whereSqlList = new ArrayList<>();

        // в случае параметров, которые могут быть, а могут и не быть, мы должны их проверить на "not null"
        if (filter.hotel_id() != null) {
            whereSqlList.add(" hotel_id = ?");
            parametrList.add(filter.hotel_id());
        }
        if (filter.beds_count() != null) {
            whereSqlList.add(" beds_count > ? ");
            parametrList.add(filter.beds_count());
        }
        if (filter.floor() != null) {
            whereSqlList.add(" floor > ? ");
            parametrList.add(filter.floor());
        }
        if (filter.included_breakfast() != null) {
            whereSqlList.add(" included_breakfast = ?");
            parametrList.add(filter.included_breakfast());
        }
        if (filter.class_id() != null) {
            whereSqlList.add(" class_id = ? ");
            parametrList.add(filter.class_id());
        }

        if (filter.price() != null) {
            whereSqlList.add(" price > ? ");
            parametrList.add(filter.price());
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
            List<EntityRoom> roomsList = new ArrayList<>();
            while (resultSet.next()) {
                roomsList.add(buildEntityRoom(
                        resultSet));
            }
            return roomsList;
        } catch (
                SQLException
                        throwable) {
            throw new DaoException(throwable);
        }
    }


    // метод для создания сущности EntityRoom
    private static EntityRoom buildEntityRoom(ResultSet resultSet) throws SQLException {
        return new EntityRoom(
                resultSet.getLong("id"),
                resultSet.getLong("hotel_id"),
                resultSet.getInt("beds_count"),
                resultSet.getInt("floor"),
                resultSet.getBoolean("included_breakfast"),
                resultSet.getLong("class_id"),
                resultSet.getInt("price"));
    }

    public static DaoRoom getINSTANCE() {
        return INSTANCE;
    }
}
