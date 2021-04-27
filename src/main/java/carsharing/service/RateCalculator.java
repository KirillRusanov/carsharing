package carsharing.service;

import carsharing.dao.model.Deal;
import carsharing.dao.model.RateType;
import org.springframework.stereotype.Component;

@Component
public interface RateCalculator {

    RateType getRateType();

    long consider(Deal deal);
}
