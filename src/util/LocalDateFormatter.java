package util;

import lombok.experimental.UtilityClass;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@UtilityClass
public class LocalDateFormatter {

    private final String PATTERN = "yyyy-MM-dd";
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    // преобразуем строку в дату, но метод .parse() может выбросить ошибку DateTimeParseException,
    // если текст не соответствует форматеру
    public LocalDateTime format(String date) {
        LocalDateTime parse = LocalDate.parse(date, FORMATTER).atStartOfDay();
        return parse;
    }

    // создаем валидатор для проверки соответствия текста форматеру
    public boolean isValid(String date) {
        try {
            format(date);
            return true;
        } catch (DateTimeException exception) {
            return false;
        }
    }

    public boolean isBefore(String date) {
        return isValid(date) && LocalDateTime.now().isBefore(format(date));
    }

    // Лучше добавлять проверку на null. Null-safe is Valid method implementation:
    public boolean isValidNullSafe(String date) {
        try {
            return Optional.ofNullable(date)
                    .map(LocalDateFormatter::format)
                    .isPresent();
        } catch (DateTimeException exception) {
            return false;
        }
    }
}
