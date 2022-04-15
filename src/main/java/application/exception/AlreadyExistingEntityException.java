package application.exception;

public class AlreadyExistingEntityException extends Exception {
    public AlreadyExistingEntityException() {
    }

    public AlreadyExistingEntityException(String message) {
        super(message);
    }

    public AlreadyExistingEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistingEntityException(Throwable cause) {
        super(cause);
    }
}
