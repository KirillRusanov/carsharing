package carsharing.service.documentService.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;

public interface PdfGenerator {

    Document createDocument(File fileReceipt) throws IOException, DocumentException;

}
