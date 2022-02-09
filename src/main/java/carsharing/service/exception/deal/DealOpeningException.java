package carsharing.service.exception.deal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DealOpeningException extends RuntimeException {

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
