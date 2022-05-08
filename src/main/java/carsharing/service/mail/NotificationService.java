package carsharing.service.mail;

import carsharing.service.exception.CarsharingException;
import org.springframework.stereotype.Service;

import javax.mail.Multipart;

@Service
public class NotificationService extends MailService {

    @Override
    protected void configureMessage(Multipart multipart, MessageInfo messageInfo) {
        throw new CarsharingException("Not implemented");
    }
}
