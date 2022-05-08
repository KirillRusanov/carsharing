package carsharing.service.exception.deal;

import carsharing.service.exception.CarsharingException;

public class DealClosingException extends CarsharingException {

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
