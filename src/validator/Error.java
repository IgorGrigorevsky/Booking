package validator;

import lombok.Value;

// создавать будем не с помощью builder'а, а статического метода "Of"
@Value(staticConstructor = "of")
public class Error {

    String code;
    String message;
}
