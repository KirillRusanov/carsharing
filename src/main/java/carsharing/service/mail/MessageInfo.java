package carsharing.service.mail;

import lombok.Builder;
import lombok.Getter;

import java.io.File;
import java.util.List;

@Builder
@Getter
public final class MessageInfo {

    private String emailFrom;
    private String emailTo;

    private String subject;
    private String nameFrom;

    private String messagePlainText;
    private String messageHtml;

    private String description;

    private List<File> attachments;
}
