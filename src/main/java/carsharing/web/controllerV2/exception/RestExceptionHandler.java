package carsharing.web.controllerV2.exception;

import carsharing.service.exception.deal.DealClosingException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
@Order(2)
public class RestExceptionHandler {

    @ResponseBody
    @ExceptionHandler(DealClosingException.class)
    public ResponseEntity<String> handleException(DealClosingException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
    }
}
