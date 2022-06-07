package carsharing.service.exception.deal;


import carsharing.service.exception.CarsharingException;

public class DealPaymentException extends CarsharingException {

    public DealPaymentException(String msg) {
        super(msg);
    }

    public DealPaymentException(String msg, Throwable cause) {
        super(msg, cause);
    }
}