package validator;

import dao.DaoHotel;
import dto.hotel.CreateHotelDto;

public class CreateHotelValidator implements Validator<CreateHotelDto> {

    // Делаем Singleton'ом, т.к. могут быть зависимости на другие bean'ы и прочее.
    private static final CreateHotelValidator INSTANCE = new CreateHotelValidator();

    private CreateHotelValidator() {
    }

    public static CreateHotelValidator getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public ValidationResult isValid(CreateHotelDto object) {

        ValidationResult validationResult = new ValidationResult();

        if (!DaoHotel.checkName(object.getName())) {
            validationResult.add(Error.of("invalid name",
                    "введен некорректный формат названия\n"
                    + "пожалуйста, используйте следующий пример: \n \"Метрополис\""));
        }
        if (!DaoHotel.checkAddress(object.getAddress())) {
            validationResult.add(Error.of("invalid address",
                    """
                            введен некорректный формат адреса
                            пожалуйста, используйте следующий пример:\s
                             г. Москва, ул. Пятницкая, д. 3 (при наличии добавьте - корп. 1"""));
        }
        if (!DaoHotel.checkPhone(object.getPhone())) {
            validationResult.add(Error.of("invalid phone",
                    """
                            введен некорректный формат номера телефона
                            пожалуйста, используйте следующий пример:\s
                             79101234567 или 74953456789"""));
        }
        if (!DaoHotel.checkEmail(object.getEmail())) {
            validationResult.add(Error.of("invalid email",
                    """
                            введен некорректный формат номера телефона
                            пожалуйста, используйте следующий пример:\s
                             java@gmail.com"""));
        }

        return validationResult;
    }
}

