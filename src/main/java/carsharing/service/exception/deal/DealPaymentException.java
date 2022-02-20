package carsharing.service.exception.deal;


public class DealPaymentException extends RuntimeException {

    public DealPaymentException(String msg) {
        super(msg);
    }

    public DealPaymentException(String msg, Throwable cause) {
        super(msg, cause);
    }
}