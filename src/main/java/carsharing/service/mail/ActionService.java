package carsharing.service.mail;

import carsharing.service.exception.mail.MailException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;

@Service
public class ActionService extends MailService {

    @Override
    protected void configureMessage(Multipart multipart, MessageInfo messageInfo) {
        try {
            MimeBodyPart messagePart = new MimeBodyPart();
            if (messageInfo.getMessageHtml() != null) {
                messagePart.setText(messageInfo.getMessageHtml(), "utf-8", "html");
            } else if (messageInfo.getMessagePlainText() != null) {
                messagePart.setText(messageInfo.getMessagePlainText(), "utf-8", "plain");
            }
            multipart.addBodyPart(messagePart);
        } catch (MessagingException e) {
            throw new MailException("Email message configuration error");
        }
    }
}
