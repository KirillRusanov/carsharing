package carsharing.service.documentService.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public abstract class PdfDocumentGenerator implements PdfGenerator {

    public Document createDocument(File filePdf) throws DocumentException, FileNotFoundException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filePdf));
        return document;
    }

    protected abstract void configureDocument(Document doc) throws DocumentException;
}
