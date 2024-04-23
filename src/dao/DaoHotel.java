package dao;

import dto.HotelFilter;
import entity.Hotel;
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

public class DaoHotel {
    // простой вариант pattern'а Singletone
    private final static DaoHotel INSTANCE = new DaoHotel();

    private DaoHotel() {
    }

    private static final String REGEX_NAME = "[А-яА-я\\-\\s]+";
    private static final String REGEX_ADDRESS = "г\\.\\s[А-Я][а-я]+\\,\\s\\ул\\.\\s[А-Яа-я\\s]+\\,\\sд\\.\\s[\\d]+(\\sкорп\\.\\s[\\d]{1,2})?";
    private static final String REGEX_PHONE = "7[\\d]{9,12}";
    private static final String REGEX_EMAIL = "(\\w+)@[a-zA-Z]{2,10}\\.[a-z]{2,6}";

    // операция CREATE одной entity (строки) в БД
    private static final String SAVE_SQL = """
            INSERT INTO hotel (name, address, phone, email, stars_id)
            VALUES (?,?,?,?,?)
            """;

    public Hotel save(Hotel hotel) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setString(2, hotel.getAddress());
            preparedStatement.setString(3, hotel.getPhone());
            preparedStatement.setString(4, hotel.getEmail());
            preparedStatement.setLong(5, hotel.getStarsId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                hotel.setId(generatedKeys.getLong("id"));
            }
        } catch (Exception throwable) {
            throw new DaoException(throwable);
        }
        return hotel;
    }

    // строка-константа для поиска всех значений из таблицы
    private static final String FIND_ALL_SQL = """
            SELECT id, name, address, phone, email, stars_id
            FROM hotel
            """;

    // метод будет возвращать все значения таблицы (список всех Entity)
    // обычно такой метод используется только для справочных таблиц
    public List<Hotel> findAll() {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Hotel> infoEntityList = new ArrayList<>();
            while (resultSet.next()) {
                infoEntityList.add(buildEntityHotel(resultSet));
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
            --SELECT (id, name, address, phone, email, stars_id)
            FROM hotel
            WHERE id = ?
            """;

    // так как возвращаемый EntityRoom может быть NULL, мы возвращаем Optional<>
    public Optional<Hotel> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // создаем объект и сразу же его возвращаем, оборачивая в Optional <>
            Hotel hotel = null;

            // по id мы можем получить: или одну сущность - тогда мы начнем создавать наш InfoEntity
            // или ни одной - тогда мы возвращаем пустой Optional <>
            if (resultSet.next()) {
                hotel = buildEntityHotel(resultSet);
            }
            return Optional.ofNullable(hotel);
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
    public List<Hotel> findAllWithFilters(HotelFilter filter) {

        // т.к. разные варианты фильтрации, то создаём коллекцию объектов для фильтрации (наших знаков "?")
        List<Object> parametrList = new ArrayList<>();

        // для фильтрации WHERE
        List<String> whereSqlList = new ArrayList<>();

        // в случае параметров, которые могут быть, а могут и не быть, мы должны их проверить на "not null"
        if (filter.id() != null) {
            whereSqlList.add(" id = ? ");
            parametrList.add(filter.id());
        }
        if (filter.name() != null) {
            whereSqlList.add(" name ILIKE ? ");
            parametrList.add("%" + filter.name() + "%");
        }
        if (filter.address() != null) {
            whereSqlList.add("ILIKE");
            parametrList.add("%" + filter.address() + "%");
        }
        if (filter.phone() != null) {
            whereSqlList.add(" phone ILIKE ? ");
            parametrList.add("%" + filter.phone() + "%");
        }
        if (filter.email() != null) {
            whereSqlList.add(" email ILIKE ? ");
            parametrList.add("%" + filter.email() + "%");
        }
        if (filter.stars_id() != null) {
            whereSqlList.add(" stars_id > ? ");
            parametrList.add(filter.stars_id());
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
            List<Hotel> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(buildEntityHotel(
                        resultSet));
            }
            return list;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    // операция UPDATE одной entity (строки) из БД
    private static final String UPDATE_SQL = """
            UPDATE hotel
            SET  name = ?,
                 address = ?,
                 phone = ?,
                 email = ?,
                 stars_id = ?
                 WHERE id = ?
            """;

    public void update(Hotel hotel) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, hotel.getName());
            preparedStatement.setString(2, hotel.getAddress());
            preparedStatement.setString(3, hotel.getPhone());
            preparedStatement.setString(4, hotel.getEmail());
            preparedStatement.setLong(5, hotel.getStarsId());
            preparedStatement.setLong(6, hotel.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    // операция DELETE одной entity (строки) из БД
    private static final String DELETE_SQL = """
            DELETE FROM hotel
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

    // метод для создания сущности EntityEmployee
    private static Hotel buildEntityHotel(ResultSet resultSet) throws SQLException {
        return new Hotel(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("address"),
                resultSet.getString("phone"),
                resultSet.getString("email"),
                resultSet.getLong("stars_id")
        );
    }

    public static boolean checkName(String name) {
        return name.matches(REGEX_NAME);
    }

    public static boolean checkAddress(String newAddress) {
        return newAddress.matches(REGEX_ADDRESS);
    }

    public static boolean checkPhone(String phone) {
        return phone.matches(REGEX_PHONE);
    }

    public static boolean checkEmail(String email) {
        return email.matches(REGEX_EMAIL);
    }

    public static DaoHotel getInstance() {
        return INSTANCE;
    }
}