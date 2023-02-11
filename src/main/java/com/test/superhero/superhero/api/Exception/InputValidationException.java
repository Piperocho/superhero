package com.test.superhero.superhero.api.Exception;

public class InputValidationException extends RuntimeException {


    public InputValidationException(String message) {
        super(message);
    }

    public InputValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
