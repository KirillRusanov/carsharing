package carsharing.service.exception.deal;

import carsharing.service.exception.CarsharingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DealOpeningException extends CarsharingException {

    public DealOpeningException() {
        super();
    }

    public DealOpeningException(String msg) {
        super(msg);
    }

    public DealOpeningException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
