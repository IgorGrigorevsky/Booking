package service;

import dao.DaoAuthentication;
import dto.authentication.CreateAuthenticationDto;
import entity.Authentication;
import exception.ValidationException;
import mapper.CreateAuthenticationMapper;
import validator.CreateAuthenticationValidator;
import validator.ValidationResult;

public class AuthenticationService {

    // паттерн Singleton
    private static final AuthenticationService INSTANCE = new AuthenticationService();

    private final CreateAuthenticationValidator createAuthenticationValidator = CreateAuthenticationValidator.getINSTANCE();
    private final DaoAuthentication daoAuthentication = DaoAuthentication.getInstance();
    private final CreateAuthenticationMapper createAuthenticationMapper = CreateAuthenticationMapper.getINSTANCE();

    private AuthenticationService() {
    }

    public static AuthenticationService getINSTANCE() {
        return INSTANCE;
    }

    public Long create(CreateAuthenticationDto createAuthenticationDto) {

        // 1 шаг: validation
        // Чтобы понять - правильные ли данные ввели - мы можем это сделать на уровне сервисов,
        // но и на уровне сервлетов, что неудобно.
        // Используют сервисы - т.к. можно пойти в БД - проверить какие-то данные на уникальность и т.д.
        // на уровне клиента - используется клиентская валидация, но она не обязательна.
        // В случае backend'а - серверная валидация - она должна быть обязательна.
        ValidationResult validResult = createAuthenticationValidator.isValid(createAuthenticationDto);
        if (!validResult.isValid()) {
            throw new ValidationException(validResult.getErrors());
        }

        // 2 шаг: map
        // После валидации, преобразуем нашу Dto в сущность (Entity),
        // т.к. мы работаем на уровне Dao с сущностями

        Authentication authentication = createAuthenticationMapper.mapFrom(createAuthenticationDto);

        // 3 шаг: hotelDao.save()
        // после преобразования Dto в Entity, используем методом .save(), чтобы сохранить преобразованную сущность из предыдущего шага
        Authentication savedAuthentication = daoAuthentication.saveFromRegistration(authentication);


        // 4 шаг: return
        // возвращаем id, саму сущность или то, что мы возвращаем в методе.
        return savedAuthentication.getPersonId();
    }

    public boolean deleteUser(Long deletePersonInfoId) {
        return daoAuthentication.delete(deletePersonInfoId);
    }
}
