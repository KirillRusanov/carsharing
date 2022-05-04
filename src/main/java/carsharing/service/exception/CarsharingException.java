package carsharing.service.exception;

public class CarsharingException extends RuntimeException {

    public CarsharingException() {
        super();
    }

    public CarsharingException(String msg) {
        super(msg);
    }

    public CarsharingException(String msg, Throwable cause) {
        super(msg, cause);
    }
}