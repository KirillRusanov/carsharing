package carsharing.service;

import carsharing.dao.model.Deal;
import carsharing.web.dto.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentManager {

    @Autowired
    private BankManager bankManager;

    public Receipt executeTransaction(Deal deal, long totalPrice) {
        String transactionNumber = bankManager.withdraw(deal.getCustomer(), totalPrice);
        return getReceipt(deal, totalPrice, transactionNumber);
    }

    private Receipt getReceipt(Deal deal, long totalPrice, String transactionNumber) {
        return Receipt.builder()
                .car(deal.getCar().getBrand())
                .dateStartDeal(deal.getStartDate())
                .dateEndDeal(LocalDateTime.now())
                .rateType(deal.getCar().getRate().getType())
                .orderNumber(deal.getId())
                .rateName(deal.getCar().getRate().getName())
                .transactionNumber(transactionNumber)
                .totalPrice(totalPrice)
                .build();
    }
}
