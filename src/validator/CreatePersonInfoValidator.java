package validator;

import dao.DaoHotel;
import dao.DaoPersonInfo;
import dto.hotel.CreateHotelDto;
import dto.personInfo.CreatePersonInfoDto;
import lombok.NoArgsConstructor;

public class CreatePersonInfoValidator implements Validator<CreatePersonInfoDto> {

    // Делаем Singleton'ом, т.к. могут быть зависимости на другие bean'ы и прочее.
    private static final CreatePersonInfoValidator INSTANCE = new CreatePersonInfoValidator();

    private CreatePersonInfoValidator() {
    }

    public static CreatePersonInfoValidator getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(CreatePersonInfoDto object) {

        ValidationResult validationResult = new ValidationResult();

        if (!DaoPersonInfo.checkFullName(object.getFullName())) {
            validationResult.add(Error.of("invalid fullName",
                    "введен некорректный формат ФИО: " + object.getFullName()
                    + "\nпожалуйста, используйте следующий пример: Петр Петрович"));
        }
        if (!DaoPersonInfo.checkPhoneNumber(object.getPhoneNumber())) {
            validationResult.add(Error.of("invalid phoneNumber",
                    "введен некорректный формат телефона: " + object.getPhoneNumber()
                    + " пожалуйста, используйте следующий пример: 89101234567"));
        }
        if (!DaoPersonInfo.checkEmail(object.getEmail())) {
            validationResult.add(Error.of("invalid email",
                    "введен некорректный формат электронной почты: " + object.getEmail()
                    + " пожалуйста, используйте следующий пример: java@gmail.com"));
        }

        return validationResult;
    }
}

