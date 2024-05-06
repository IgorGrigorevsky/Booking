package service;

import dao.DaoPersonInfo;
import dto.personInfo.CreatePersonInfoDto;
import dto.personInfo.PersonInfoDto;
import dto.personInfo.PersonInfoFilter;
import entity.PersonInfo;
import exception.ValidationException;
import mapper.CreatePersonInfoMapper;
import mapper.CreatePersonInfoMapperWhenLoggin;
import validator.CreatePersonInfoValidator;
import validator.ValidationResult;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class PersonInfoService {

    // паттерн Singleton
    private static final PersonInfoService INSTANCE = new PersonInfoService();

    private final CreatePersonInfoValidator createPersonInfoValidator = CreatePersonInfoValidator.getINSTANCE();
    private final DaoPersonInfo daoPersonInfo = DaoPersonInfo.getInstance();
    private final CreatePersonInfoMapper createPersonInfoMapper = CreatePersonInfoMapper.getINSTANCE();
    private final CreatePersonInfoMapperWhenLoggin createPersonInfoMapperWhenLoggin = CreatePersonInfoMapperWhenLoggin.getINSTANCE();

    private PersonInfoService() {
    }

    public static PersonInfoService getINSTANCE() {
        return INSTANCE;
    }

    public Long create(CreatePersonInfoDto createPersonInfoDto) {

        // 1 шаг: validation
        ValidationResult validResult = createPersonInfoValidator.isValid(createPersonInfoDto);
        if (!validResult.isValid()) {
            throw new ValidationException(validResult.getErrors());
        }

        // 2 шаг: map Dto -> Entity
        PersonInfo personInfo = createPersonInfoMapper.mapFrom(createPersonInfoDto);

        // 3 шаг: hotelDao.save()
        // после того, как мы преобразовали Dto в Entity, мы можем воспользоваться методом .save()
        // HotelDao.save() -> для того, чтобы сохранить преобразованную сущность из предыдущего шага
        PersonInfo savedPersonInfo = daoPersonInfo.save(personInfo);


        // 4 шаг: return
        // возвращаем id, саму сущность или то, что мы возвращаем в методе.
        return savedPersonInfo.getId();
    }

    public PersonInfoDto save(PersonInfo personInfo) {
        PersonInfo savedPersonInfo = daoPersonInfo.save(personInfo);
        return new PersonInfoDto(savedPersonInfo.getId(), savedPersonInfo.getFullName(),
                savedPersonInfo.getPhoneNumber(), savedPersonInfo.getEmail());
    }

    public List<PersonInfoDto> findAll() {

        // получив на выходе List -> мы преобразовываем объекты EmployeePosition в EmployeePositionDto
        return daoPersonInfo.findAll().stream()
                .map(personInfo -> new PersonInfoDto(personInfo.getId(), personInfo.getFullName(),
                        personInfo.getPhoneNumber(), personInfo.getEmail()
                )).collect(toList());
    }

    public List<PersonInfoDto> findById(Long id) {
        return daoPersonInfo.findById(id).stream()
                .map(personInfo -> new PersonInfoDto(personInfo.getId(), personInfo.getFullName(),
                        personInfo.getPhoneNumber(), personInfo.getEmail()
                )).collect(toList());
    }

    public List<PersonInfoDto> findAllWithFilters(PersonInfoFilter filter) {
        return daoPersonInfo.findAllWithFilters(filter).stream()
                .map(personInfo -> new PersonInfoDto(personInfo.getId(), personInfo.getFullName(),
                        personInfo.getPhoneNumber(), personInfo.getEmail()
                )).collect(toList());
    }

    public boolean delete(Long id) {
        return daoPersonInfo.delete(id);
    }

    public Optional<PersonInfoDto> login(String email, String password) {
        return daoPersonInfo.findByPasswordAndEmail(email, password)
                .map(createPersonInfoMapperWhenLoggin::mapFrom);
    }
}