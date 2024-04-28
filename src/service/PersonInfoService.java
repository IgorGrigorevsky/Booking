package service;

import dao.DaoPersonInfo;
import dto.personInfo.PersonInfoDto;
import dto.personInfo.PersonInfoFilter;
import entity.PersonInfo;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class PersonInfoService {

    // паттерн Singleton
    private static final PersonInfoService INSTANCE = new PersonInfoService();

    private final DaoPersonInfo daoPersonInfo = DaoPersonInfo.getInstance();

    private PersonInfoService() {
    }

    public static PersonInfoService getINSTANCE() {
        return INSTANCE;
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
}