package dao;

import entity.Authentication;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class DaoAuthentication implements Dao<Long, Authentication> {

    // простой вариант pattern'а Singletone
    private final static DaoAuthentication INSTANCE = new DaoAuthentication();

    // константа для проверки пароля
    private static final String REGEX_PASSWORD = "\\w+";

    private DaoAuthentication() {
    }

    @Override
    public List<Authentication> findAll() {
        return null;
    }

    @Override
    public Optional<Authentication> findById(Long id) {
        return Optional.empty();
    }


    // операция DELETE одной entity (строки) из БД
    private static final String DELETE_SQL = """
            DELETE FROM authentication
            WHERE person_info_id = ?
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

    @Override
    public void update(Authentication entity) {

    }

    @Override
    public Authentication save(Authentication entity) {
        return null;
    }

    // операция CREATE при регистрации пользователя из JSP
    private static final String SAVE_SQL_REGISTRATION = """
            INSERT INTO authentication (person_info_id, password)
            VALUES (?,?)
            """;

    public Authentication saveFromRegistration(Authentication authentication) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL_REGISTRATION)) {
            preparedStatement.setLong(1, authentication.getPersonId());
            preparedStatement.setString(2, authentication.getPassword());
            preparedStatement.executeUpdate();
        } catch (Exception throwable) {
            throw new DaoException(throwable);
        }
        return authentication;
    }

    public static boolean checkPassword(String password) {
        return password.matches(REGEX_PASSWORD);
    }

    public static DaoAuthentication getInstance() {
        return INSTANCE;
    }
}
