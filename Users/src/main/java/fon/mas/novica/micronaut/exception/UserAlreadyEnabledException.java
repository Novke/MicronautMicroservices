package fon.mas.novica.micronaut.exception;

public class UserAlreadyEnabledException extends RuntimeException{

    public UserAlreadyEnabledException() {
    }

    public UserAlreadyEnabledException(String message) {
        super(message);
    }

    public UserAlreadyEnabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyEnabledException(Throwable cause) {
        super(cause);
    }
}
