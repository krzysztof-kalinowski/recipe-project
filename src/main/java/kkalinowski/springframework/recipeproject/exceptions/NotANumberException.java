package kkalinowski.springframework.recipeproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Krzysztof Kalinowski on 09/12/2019.
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotANumberException extends NumberFormatException{

    public NotANumberException() {
    }

    public NotANumberException(String s) {
        super(s);
    }
}
