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
                    "введен некорректный формат названия: " + object.getName()
                    + "пожалуйста, используйте следующий пример без кавычек: \"Метрополис\""));
        }
        if (!DaoHotel.checkAddress(object.getAddress())) {
            validationResult.add(Error.of("invalid address",
                    "введен некорректный формат адрема: " + object.getAddress()
                    + "пожалуйста, используйте следующий пример:\s" +
                    "г. Москва, ул. Пятницкая, д. 3 (при наличии добавьте - корп. 1"));
        }
        if (!DaoHotel.checkPhone(object.getPhone())) {
            validationResult.add(Error.of("invalid phone",
                    "введен некорректный формат телефона: " + object.getPhone()
                    + "пожалуйста, используйте следующий пример: 79101234567 или 74953456789"));
        }
        if (!DaoHotel.checkEmail(object.getEmail())) {
            validationResult.add(Error.of("invalid email",
                    "введен некорректный формат электронной почты: " + object.getEmail()
                    + "пожалуйста, используйте следующий пример: java@gmail.com"));
        }

        return validationResult;
    }
}

