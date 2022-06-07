package carsharing.service.mail;

import carsharing.service.exception.mail.MailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;

@Service
public abstract class MailService implements Mailer {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMessage(MessageInfo messageInfo) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            configureEmail(messageHelper, messageInfo);
            Multipart multipart = new MimeMultipart();
            configureMessage(multipart, messageInfo);
            addAttachments(multipart, messageInfo);
            mimeMessage.setContent(multipart);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException | IOException e) {
            throw new MailException("Email message configuration error", e.getCause());
        }
    }

    protected void configureEmail(MimeMessageHelper messageHelper, MessageInfo messageInfo) throws MessagingException {
        messageHelper.setTo(messageInfo.getEmailTo());
        messageHelper.setFrom( messageInfo.getEmailFrom() != null ? messageInfo.getEmailFrom() : "Undefined");
        messageHelper.setSubject(messageInfo.getSubject());
    }

    abstract protected void configureMessage(Multipart multipart, MessageInfo messageInfo);

    protected void addAttachments(Multipart multipart, MessageInfo messageInfo) throws IOException, MessagingException {
            MimeBodyPart attachmentPart = new MimeBodyPart();
            for (File file : messageInfo.getAttachments()) {
                attachmentPart.attachFile(file);
            }
            multipart.addBodyPart(attachmentPart);
    }
}
