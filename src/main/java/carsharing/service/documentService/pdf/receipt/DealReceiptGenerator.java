package carsharing.service.documentService.pdf.receipt;

import carsharing.web.dto.Receipt;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.stereotype.Component;

@Component
public class DealReceiptGenerator extends ReceiptGenerator {

    @Override
    protected String getReceiptType() {
        return "Deal receipt";
    }

    @Override
    protected void fillReceipt(Document doc, Receipt receipt) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setSpacingBefore(20f);
        addRowToTable(table, "Order number ", String.valueOf(receipt.getOrderNumber()));
        addRowToTable(table, "Transaction number ", receipt.getTransactionNumber());
        addRowToTable(table, "Rate ", receipt.getRateName());
        addRowToTable(table, "Rate Type ", receipt.getRateType().name());
        addRowToTable(table, "Car ", receipt.getCar());
        addRowToTable(table, "Start Date ", receipt.getStartDate());
        addRowToTable(table, "End Date ", receipt.getEndDate());
        addRowToTable(table, "TOTAL COST -->  ", receipt.getTotalPrice() + " $");
        doc.add(table);
    }

    private void addRowToTable(PdfPTable table, String rowName, String param) {
        PdfPCell cellName = new PdfPCell(new Phrase(rowName));
        cellName.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cellName);
        PdfPCell cellParam = new PdfPCell(new Phrase(param));
        cellParam.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(cellParam);
    }
}
