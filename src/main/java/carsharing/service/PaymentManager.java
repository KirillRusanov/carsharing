package carsharing.service;

import carsharing.dao.model.Deal;
import carsharing.dao.model.RateType;
import carsharing.web.dto.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentManager {

    @Autowired
    private BankManager bankManager;

    @Autowired
    private RateCalculatorByHours rateCalculatorByHours;
    @Autowired
    private RateCalculatorByDays rateCalculatorByDays;


    private static final Logger LOG = LoggerFactory.getLogger(PaymentManager.class);

    private Map<RateType, RateCalculator> calculators = new HashMap<>();
//            = Map.of(
//            RateType.HOURLY, new RateCalculatorByHours(),
//            RateType.DAILY, new RateCalculatorByDays()
//    );

    @PostConstruct
    public void init() {
        calculators.put(RateType.HOURLY, rateCalculatorByHours);
        calculators.put(RateType.DAILY, rateCalculatorByDays);
    }

    public Receipt serve(Deal deal) {
        LOG.info(deal.getId() + " is in the process of completion, payment is due");
        for (Map.Entry<RateType, RateCalculator> calculator : calculators.entrySet()) {
            if (calculator.getKey().equals(deal.getCar().getRate().getType())) {
                return calculator.getValue().consider(deal);
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "this type of rates does not exist");
    }

    Receipt getReceipt(Deal deal, Date dateStartDeal, Date dateEndDeal, long totalPrice) {

        String transactionNumber = bankManager.withdraw(deal.getCustomer().getCards(), totalPrice);

        return Receipt.builder()
                .car(deal.getCar().getBrand())
                .dateStartDeal(dateStartDeal)
                .dateEndDeal(dateEndDeal)
                .rateType(deal.getCar().getRate().getType())
                .orderNumber(deal.getId())
                .rateName(deal.getCar().getRate().getName())
                .transactionNumber(transactionNumber)
                .totalPrice(totalPrice)
                .build();
    }
}
