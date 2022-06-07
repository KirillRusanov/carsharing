package carsharing.service;

import carsharing.dao.model.Deal;
import carsharing.dao.model.RateType;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
public class RateCalculatorByHours implements RateCalculator {

    @Autowired
    private PaymentManager paymentManager;

    @Override
    public RateType getRateType() {
        return RateType.HOURLY;
    }

    @Override
    public long consider(Deal deal) {
        long totalTime = (LocalDateTime.now().getHour() - deal.getStartDate().getHour());
        return totalTime != 0 ? totalTime * deal.getCar().getRate().getCost() : deal.getCar().getRate().getCost();
    }
}
