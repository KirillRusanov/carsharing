package carsharing.service;

import carsharing.dao.model.Customer;
import carsharing.dao.model.Deal;
import carsharing.web.dto.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class PaymentManager {

    @Autowired
    private BankService bankService;
    @Autowired
    private CustomerService customerService;

    public Receipt executeTransaction(Deal deal, long totalPrice) {
        String transactionNumber = bankService.withdraw(deal.getCustomer(), totalPrice);
        return getReceipt(deal, totalPrice, transactionNumber);
    }

    public Receipt withdrawFundsFromAccount(Deal deal, long totalPrice) {
        Customer customer = customerService.getCustomerByEmail(deal.getCustomer().getEmail());
        customer.setBalance(customer.getBalance().subtract(BigDecimal.valueOf(totalPrice)));
        customerService.save(customer);
        return getReceipt(deal, totalPrice, getDebitNumber());
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

    private String getDebitNumber() {
        return UUID.randomUUID().toString();
    }
}
