package util;

import lombok.experimental.UtilityClass;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class LocalDateFormatter {

    private final String PATTERN = "yyyy-MM-dd HH:mm";
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

    public LocalDateTime format(String date) {
        return LocalDateTime.parse(date, FORMATTER);
    }

    public boolean isValid(String date) {
        try {
            format(date);
            return true;
        } catch (DateTimeException exception) {
            return false;
        }
    }
}
