package carsharing.service;

import carsharing.dao.model.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class BankManager {

    private static final Logger LOG = LoggerFactory.getLogger(BankManager.class);

    public String withdraw(List<Card> cards, long totalPrice) {
        for(Card card : cards) {
            if(request(card, totalPrice)) {
                LOG.info("Payment by card -" + card.getNumber() + "- was successful. Total price: " + totalPrice);
                return UUID.randomUUID().toString();
            }
        }
        throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, "Denied payment. Insufficient funds on the card");
    }

    private boolean request(Card card, long totalPrice) {
        LOG.info(card.getNumber() + " - requested balance" + " access code: " + card.getCode());
        long balanceCard = new Random().nextLong();
        if(totalPrice < balanceCard) {
            LOG.info("Debiting funds from the card - " + card.getNumber() + " access code: " + card.getCode());
            return true;
        }
        return false;
    }

}
