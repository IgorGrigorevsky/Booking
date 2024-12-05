package service;

import dao.DaoAuthorisation;
import dto.authorisation.CreateAuthorisationDto;
import entity.Authorisation;
import mapper.CreateAuthorisationMapper;

import java.util.List;

public class AuthorisationService {


    // паттерн Singleton
    private static final AuthorisationService INSTANCE = new AuthorisationService();

    private final DaoAuthorisation daoAuthorisation = DaoAuthorisation.getInstance();
    private final CreateAuthorisationMapper createAuthorisationMapper = CreateAuthorisationMapper.getINSTANCE();

    private AuthorisationService() {
    }

    public static AuthorisationService getINSTANCE() {
        return INSTANCE;
    }

    public Long create(CreateAuthorisationDto createAuthorisationDto) {

        // 1 шаг: validation
        // Отсутствует

        // 2 шаг: map
        // После валидации, преобразуем нашу Dto в сущность (Entity),
        // т.к. мы работаем на уровне Dao с сущностями

        Authorisation authorisation = createAuthorisationMapper.mapFrom(createAuthorisationDto);

        // 3 шаг: hotelDao.save()
        // после преобразования Dto в Entity, используем методом .save(), чтобы сохранить преобразованную сущность из предыдущего шага
        Authorisation savedAuthorisation = daoAuthorisation.saveFromRegistration(authorisation);


        // 4 шаг: return
        // возвращаем id, саму сущность или то, что мы возвращаем в методе.
        return savedAuthorisation.getPersonInfoId();
    }

    public List<CreateAuthorisationDto> isClient(Long id) {
        return daoAuthorisation.findById(id).stream()
                .map(authorisation -> new CreateAuthorisationDto(authorisation.getPersonInfoId(),
                        authorisation.getRoleId(), authorisation.getIsClient()))
                .toList();
    }

    public boolean deleteUser(Long deletePersonInfoId) {
        return daoAuthorisation.delete(deletePersonInfoId);
    }
}
