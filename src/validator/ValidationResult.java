package validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    // можно было бы сделать List <String> - но в таком случае не получится интернационализировать наши ошибки
    // Например, сделаем на английском - всегда будут отображаться на английском
    // Поэтому лучше создать ошибку типа Error, в которой будет два поля: код и сама ошибка или просто код ошибки
    @Getter
    private final List<Error> errors = new ArrayList<>();

    // метод добавления соответствующей ошибки, если мы ее нашли
    public void add(Error error) {
        this.errors.add(error);
    }

    // метод для получения этих ошибок
    // с помощью аннотации

    // метод определения - валидный результат или нет
    public boolean isValid() {
        return errors.isEmpty();
    }
}
