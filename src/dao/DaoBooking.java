package dao;

import dto.BookingFilter;
import entity.Booking;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.joining;

public class DaoBooking implements Dao<Long, Booking> {
    // простой вариант pattern'а Singletone
    private final static DaoBooking INSTANCE = new DaoBooking();

    private DaoBooking() {
    }

    // операция CREATE одной entity (строки) в БД
    private static final String SAVE_SQL = """
            INSERT INTO booking (client_id, room_id, date_from, date_to, is_approved, is_paid)
            VALUES (?,?,?,?,?,?)
            """;

    @Override
    public Booking save(Booking booking) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, booking.getClientId());
            preparedStatement.setLong(2, booking.getRoomId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(booking.getDateFrom()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(booking.getDateTo()));
            preparedStatement.setBoolean(5, booking.getIsApproved());
            preparedStatement.setBoolean(6, booking.getIsPaid());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                booking.setId(generatedKeys.getLong("id"));
            }
        } catch (Exception throwable) {
            throw new DaoException(throwable);
        }
        return booking;
    }

    // строка-константа для поиска всех значений из таблицы
    private static final String FIND_ALL_SQL = """
            SELECT id, client_id, room_id, date_from, date_to, is_approved, is_paid
            FROM booking
            """;

    // метод будет возвращать все значения таблицы (список всех Entity)
    // обычно такой метод используется только для справочных таблиц
    @Override
    public List<Booking> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Booking> infoEntityList = new ArrayList<>();
            while (resultSet.next()) {
                infoEntityList.add(buildEntityBooking(resultSet));
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
            --SELECT (id, client_id, room_id, date_from, date_to, is_approved, is_paid)
            FROM booking
            WHERE id = ?
            """;

    // так как возвращаемый EntityRoom может быть NULL, мы возвращаем Optional<>
    @Override
    public Optional<Booking> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // создаем объект и сразу же его возвращаем, оборачивая в Optional <>
            Booking booking = null;

            // по id мы можем получить: или одну сущность - тогда мы начнем создавать наш InfoEntity
            // или ни одной - тогда мы возвращаем пустой Optional <>
            if (resultSet.next()) {
                booking = buildEntityBooking(resultSet);
            }
            return Optional.ofNullable(booking);
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
    public List<Booking> findAllWithFilters(BookingFilter filter) {

        // т.к. разные варианты фильтрации, то создаём коллекцию объектов для фильтрации (наших знаков "?")
        List<Object> parametrList = new ArrayList<>();

        // для фильтрации WHERE
        List<String> whereSqlList = new ArrayList<>();

        // в случае параметров, которые могут быть, а могут и не быть, мы должны их проверить на "not null"
        if (filter.id() != null) {
            whereSqlList.add(" id = ? ");
            parametrList.add(filter.id());
        }
        if (filter.client_id() != null) {
            whereSqlList.add(" client_id = ? ");
            parametrList.add(filter.client_id());
        }
        if (filter.room_id() != null) {
            whereSqlList.add(" room_id = ? ");
            parametrList.add(filter.room_id());
        }
        if (filter.date_from() != null) {
            whereSqlList.add(" date_from >= ? ");
            parametrList.add(filter.date_from());
        }
        if (filter.date_to() != null) {
            whereSqlList.add(" date_to <= ? ");
            parametrList.add(filter.date_to());
        }
        if (filter.is_approved() != null) {
            whereSqlList.add(" is_approved = ? ");
            parametrList.add(filter.is_approved());
        }
        if (filter.is_paid() != null) {
            whereSqlList.add(" is_paid = ? ");
            parametrList.add(filter.is_paid());
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
            List<Booking> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(buildEntityBooking(
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
            UPDATE booking
            SET  client_id = ?,
            room_id =?,
            date_from = ?,
            date_to = ?,
            is_approved = ?,
            is_paid = ?
            WHERE id = ?
            """;

    @Override
    public void update(Booking booking) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setLong(1, booking.getClientId());
            preparedStatement.setLong(2, booking.getRoomId());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(booking.getDateFrom()));
            preparedStatement.setTimestamp(4, Timestamp.valueOf(booking.getDateTo()));
            preparedStatement.setBoolean(5, booking.getIsApproved());
            preparedStatement.setBoolean(6, booking.getIsPaid());
            preparedStatement.setLong(7, booking.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    // операция DELETE одной entity (строки) из БД
    private static final String DELETE_SQL = """
            DELETE FROM booking
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

    // метод для создания сущности EntityBooking
    private static Booking buildEntityBooking(ResultSet resultSet) throws SQLException {
        return new Booking(
                resultSet.getLong("id"),
                resultSet.getLong("client_id"),
                resultSet.getLong("room_id"),
                resultSet.getTimestamp("date_from").toLocalDateTime(),
                resultSet.getTimestamp("date_to").toLocalDateTime(),
                resultSet.getBoolean("is_approved"),
                resultSet.getBoolean("is_paid"));
    }

    public static LocalDateTime parseTime(String timeString) {
        return LocalDateTime.parse(timeString, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    public static DaoBooking getInstance() {
        return INSTANCE;
    }
}
