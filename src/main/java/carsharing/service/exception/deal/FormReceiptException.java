package carsharing.service.exception.deal;

public class FormReceiptException extends DealClosingException {

    public FormReceiptException() {
        super();
    }

    public FormReceiptException(String msg) {
        super(msg);
    }

    public FormReceiptException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
