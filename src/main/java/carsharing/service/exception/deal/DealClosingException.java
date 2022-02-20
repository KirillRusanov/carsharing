package carsharing.service.exception.deal;

public class DealClosingException extends RuntimeException {

    public DealClosingException() {
        super();
    }

    public DealClosingException(String msg) {
        super(msg);
    }

    public DealClosingException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
