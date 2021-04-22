package carsharing.service;

import carsharing.dao.model.Deal;
import carsharing.web.dto.Receipt;

public abstract class RateCalculator {

    public abstract Receipt consider(Deal deal);
}
