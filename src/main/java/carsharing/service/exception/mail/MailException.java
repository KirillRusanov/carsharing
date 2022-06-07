package carsharing.service.exception.mail;

public class MailException extends RuntimeException {

    public MailException() {
        super();
    }

    public MailException(String msg) {
        super(msg);
    }

    public MailException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
