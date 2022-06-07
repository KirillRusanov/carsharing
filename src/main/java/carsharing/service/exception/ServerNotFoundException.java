package carsharing.service.exception;

public class ServerNotFoundException extends RuntimeException {

    public ServerNotFoundException() {
        super();
    }

    public ServerNotFoundException(String msg) {
        super(msg);
    }

    public ServerNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
