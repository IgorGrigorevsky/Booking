package validator;

import dao.DaoAuthentication;
import dto.authentication.CreateAuthenticationDto;

public class CreateAuthenticationValidator implements Validator<CreateAuthenticationDto> {

    // Делаем Singleton'ом, т.к. могут быть зависимости на другие bean'ы и прочее.
    private static final CreateAuthenticationValidator INSTANCE = new CreateAuthenticationValidator();

    private CreateAuthenticationValidator() {
    }

    public static CreateAuthenticationValidator getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(CreateAuthenticationDto object) {

        ValidationResult validationResult = new ValidationResult();

        if (!DaoAuthentication.checkPassword(object.getPassword())) {
            validationResult.add(Error.of("invalid password",
                    "введен некорректный формат пароля: " + object.getPassword()
                    + "пожалуйста, используйте целые числа, буквы и подчеркивание"));
        }

        return validationResult;
    }
}
