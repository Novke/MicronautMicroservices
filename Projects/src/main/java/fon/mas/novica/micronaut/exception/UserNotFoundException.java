package fon.mas.novica.micronaut.exception;


public class UserNotFoundException extends RuntimeException{

    private final String id;

    public String getId() {
        return id;
    }

    public UserNotFoundException(String message) {
        super(message);
        id = message;
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
        id = message;
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
        id = "unknown";
    }
}
