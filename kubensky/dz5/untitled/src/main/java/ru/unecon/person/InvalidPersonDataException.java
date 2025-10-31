package ru.unecon.person;

public class InvalidPersonDataException extends RuntimeException {
    public InvalidPersonDataException(String message) {
        super(message);
    }

    public InvalidPersonDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
