package carsharing.service;

import carsharing.dao.model.Deal;
import carsharing.dao.model.Rate;
import carsharing.web.dto.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DealManager {

    @Autowired
    private List<RateCalculator> rateCalculatorList;
    @Autowired
    private PaymentManager paymentManager;

    private static final Logger LOG = LoggerFactory.getLogger(DealManager.class);

    public Receipt serve(Deal deal) throws ClassNotFoundException {
        RateCalculator rateCalculator = getRateCalculator(deal.getCar().getRate());
        long totalPrice = rateCalculator.consider(deal);
        LOG.info(deal.getId() + " is in the process of completion, payment is due");
        return paymentManager.withdrawFundsFromAccount(deal, totalPrice);
    }

    private RateCalculator getRateCalculator(Rate rate) throws ClassNotFoundException {
        Optional<RateCalculator> calculator = rateCalculatorList
                .stream()
                .filter(rateCalculator -> rateCalculator.getRateType().equals(rate.getType()))
                .findFirst();
        if (calculator.isPresent()) {
            return calculator.get();
        } else {
            throw new ClassNotFoundException("this type of rates does not exist");
        }
    }
}
