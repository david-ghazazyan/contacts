package am.iunetworks.exception;

public class ExitException extends Exception{

    public ExitException() {
    }

    public ExitException(String message) {
        super(message);
    }

    public ExitException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExitException(Throwable cause) {
        super(cause);
    }

    public ExitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}