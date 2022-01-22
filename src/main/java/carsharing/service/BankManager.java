package carsharing.service;

import carsharing.dao.model.Card;
import carsharing.dao.model.Customer;
import carsharing.service.exception.DealPaymentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.UUID;

@Component
public class BankManager {

    private static final Logger LOG = LoggerFactory.getLogger(BankManager.class);

    public String withdraw(Customer customer, long totalPrice) {
        for(Card card : customer.getCards()) {
            if(request(card, totalPrice)) {
                LOG.info("Payment by card -" + card.getNumber() + "- was successful. Total price: " + totalPrice);
                return UUID.randomUUID().toString();
            }
        }
        throw new DealPaymentException("Denied payment. Not enough money on the card");
    }
    // TODO - need to delete this random :)
    private boolean request(Card card, long totalPrice) {
        LOG.info(card.getNumber() + " - requested balance" + " access code: " + card.getCode());
        long balanceCard = new Random().nextLong() * 130;
        if(totalPrice <= balanceCard) {
            LOG.info("Debiting funds from the card - " + card.getNumber() + " access code: " + card.getCode());
            return true;
        }
        return false;
    }

}
