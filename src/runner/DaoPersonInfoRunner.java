package runner;

import dao.DaoPersonInfo;
import dto.PersonInfoFilter;
import entity.PersonInfo;

import java.util.List;
import java.util.Optional;

public class DaoPersonInfoRunner {


    public static void main(String[] args) {

        // CREATE
        savePersonInfo("Инакентий Инакентьевич", "89534447755", "inakent@gmail.com");

        // READ
        selectAll();
        selectAllWithFilter(5, 0, null, "инак", "953", null);

        // UPDATE
        updatePersonInfoFullName(21L, "Петрушка Петрухова");
        updatePersonInfoPhoneNumber(21L, "89100000000");
        updatePersonInfoEmail(21L, "adasd@adasd.ru");

        // DELETE
        deletePersonInfo(21L);
    }

    private static void savePersonInfo(String fullName, String phoneNumber, String email) {
        if (DaoPersonInfo.checkFullName(fullName) &&
            DaoPersonInfo.checkPhoneNumber(phoneNumber) &&
            DaoPersonInfo.checkEmail(email)) {

            DaoPersonInfo daoPersonInfo = DaoPersonInfo.getInstance();
            PersonInfo personInfo = new PersonInfo();

            personInfo.setFullName(fullName);
            personInfo.setPhoneNumber(phoneNumber);
            personInfo.setEmail(email);

            PersonInfo savedValue = daoPersonInfo.save(personInfo);
            System.out.println("Сохраненная запись: \n" + savedValue);
        } else {
            System.out.println("введен некорректный формат ФИО, номера телефона или email");
            System.out.println("пожалуйста, используйте следующий пример: \n Петр Петрович \n 89101234567 или 84953456789 \n java@gmail.com");
        }
    }

    private static void selectAll() {
        List<PersonInfo> all = DaoPersonInfo.getInstance().findAll();
        System.out.println(all);
    }

    private static void selectAllWithFilter(
            int limit, int offset, Long id, String fullName, String phoneNumber, String email) {


        PersonInfoFilter filter = new PersonInfoFilter(limit, offset, id, fullName, phoneNumber, email);
        List<PersonInfo> allWithFilters = DaoPersonInfo.getInstance().findAllWithFilters(filter);
        System.out.println(allWithFilters);
    }

    private static void updatePersonInfoFullName(Long personInfoId, String newFullName) {
        if (DaoPersonInfo.checkFullName(newFullName)) {

            DaoPersonInfo instance = DaoPersonInfo.getInstance();
            Optional<PersonInfo> maybeEntity = instance.findById(personInfoId);
            System.out.println("имеющиеся full_name: \n" + maybeEntity);

            // так как у нас Optional, сначала проверяем есть ли объект
            maybeEntity.ifPresent(personInfo -> {
                personInfo.setFullName(newFullName);
                instance.update(personInfo);
            });
            System.out.println("измененные full_name: \n" + maybeEntity);
        } else {
            System.out.println("введен некорректный формат ФИО: " + newFullName);
            System.out.println("пожалуйста, используйте следующий пример: Петр Петрович");
        }
    }


    private static void updatePersonInfoPhoneNumber(Long personInfoId, String newPhoneNumber) {
        if (DaoPersonInfo.checkPhoneNumber(newPhoneNumber)) {

            DaoPersonInfo instance = DaoPersonInfo.getInstance();
            Optional<PersonInfo> maybeEntity = instance.findById(personInfoId);
            System.out.println("имеющийся phone_number: \n" + maybeEntity);

            // так как у нас Optional, сначала проверяем есть ли объект
            maybeEntity.ifPresent(personInfo -> {
                personInfo.setPhoneNumber(newPhoneNumber);
                instance.update(personInfo);
            });
            System.out.println("измененный phone_number: \n" + maybeEntity);
        } else {
            System.out.println("введен некорректный формат телефона - " + newPhoneNumber);
            System.out.println("пожалуйста, используйте следующий пример: 89101234567");
        }
    }

    private static void updatePersonInfoEmail(Long personInfoId, String newEmail) {
        if (DaoPersonInfo.checkEmail(newEmail)) {

            DaoPersonInfo instance = DaoPersonInfo.getInstance();
            Optional<PersonInfo> maybeEntity = instance.findById(personInfoId);
            System.out.println("имеющийся email: \n" + maybeEntity);

            // так как у нас Optional, сначала проверяем есть ли объект
            maybeEntity.ifPresent(personInfo -> {
                personInfo.setEmail(newEmail);
                instance.update(personInfo);
            });
            System.out.println("измененный email: \n" + maybeEntity);
        } else {
            System.out.println("введен некорректный формат электронной почты - " + newEmail);
            System.out.println("пожалуйста, используйте следующий пример: java@gmail.com");
        }
    }

    private static void deletePersonInfo(long id) {
        DaoPersonInfo daoPersonInfo = DaoPersonInfo.getInstance();
        boolean result = daoPersonInfo.delete(id);
        System.out.println("Получилось удалить запись? " + result);
    }
}
