package dao;

import entity.Authorisation;
import exception.DaoException;
import util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DaoAuthorisation implements Dao<Long, Authorisation> {

    // простой вариант pattern'а Singletone
    private final static DaoAuthorisation INSTANCE = new DaoAuthorisation();

    // операция CREATE при регистрации пользователя из JSP
    private static final String SAVE_SQL_REGISTRATION = """
            INSERT INTO authorisation (person_info_id, role_id, is_client)
            VALUES (?,?,?)
            """;

    public Authorisation saveFromRegistration(Authorisation authorisation) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL_REGISTRATION)) {
            preparedStatement.setLong(1, authorisation.getPersonInfoId());
            preparedStatement.setString(2, authorisation.getRoleId());
            preparedStatement.setBoolean(3, authorisation.getIsClient());
            preparedStatement.executeUpdate();
        } catch (Exception throwable) {
            throw new DaoException(throwable);
        }
        return authorisation;
    }

    @Override
    public List<Authorisation> findAll() {
        return null;
    }

    private static final String FIND_BY_ID_SQL = """
            Select *
            FROM authorisation
            WHERE person_info_id = ?
            """;

    @Override
    public Optional<Authorisation> findById(Long id) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            // создаем объект и сразу же его возвращаем, оборачивая в Optional <>
            Authorisation authorisation = null;

            // по id мы можем получить: или одну сущность - тогда мы начнем создавать наш InfoEntity
            // или ни одной - тогда мы возвращаем пустой Optional <>
            if (resultSet.next()) {
                authorisation = buildEntityAuthorisation(resultSet);
            }
            return Optional.ofNullable(authorisation);
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    // метод для создания сущности EntityRoom
    private static Authorisation buildEntityAuthorisation(ResultSet resultSet) throws SQLException {
        return new Authorisation(
                resultSet.getLong("person_info_id"),
                resultSet.getString("role_id"),
                resultSet.getBoolean("is_client"));
    }

    // операция DELETE одной entity (строки) из БД
    private static final String DELETE_SQL = """
            DELETE FROM authorisation
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
    public void update(Authorisation entity) {

    }

    @Override
    public Authorisation save(Authorisation entity) {
        return null;
    }

    public static DaoAuthorisation getInstance() {
        return INSTANCE;
    }
}
