package carsharing.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DealPaymentException extends RuntimeException {

    private HttpStatus httpStatus;

    public DealPaymentException(String msg) {
        super(msg);
    }

    public DealPaymentException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}