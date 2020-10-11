package de.anna.imageConverter.exception;

public class ParameterFormatException extends RuntimeException {

    public ParameterFormatException(String message, Exception exception) {
        super(message, exception);
    }
}
