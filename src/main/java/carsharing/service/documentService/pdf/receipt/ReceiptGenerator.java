package carsharing.service.documentService.pdf.receipt;

import carsharing.service.documentService.pdf.PdfDocumentGenerator;
import carsharing.web.dto.Receipt;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public abstract class ReceiptGenerator extends PdfDocumentGenerator {

    public File createReceipt(Receipt receipt) throws Exception {
        try {
            File fileReceipt = File.createTempFile(UUID.randomUUID().toString(), ".pdf");
            Document document = createDocument(fileReceipt);
            document.open();
            collectDocument(document, receipt);
            document.close();
            return fileReceipt;
        } catch (IOException | DocumentException e) {
            throw new Exception(); // TODO add Custom Exception
        }
    }

    protected abstract String getReceiptType();

    private void collectDocument(Document doc, Receipt receipt) throws IOException, DocumentException {
        configureDocument(doc);
        fillReceipt(doc, receipt);
    }

    @Override
    protected void configureDocument(Document doc) throws DocumentException {
        doc.setMargins(0f, 0f, 45, 20);
        Paragraph paragraph = new Paragraph(getReceiptType());
        paragraph.setAlignment(Element.ALIGN_CENTER);
        doc.add(paragraph);
    }

    protected abstract void fillReceipt(Document doc, Receipt receipt) throws DocumentException;
}
