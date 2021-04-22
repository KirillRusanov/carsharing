package carsharing.service;

import carsharing.dao.model.Deal;
import carsharing.web.dto.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RateCalculatorByDays extends RateCalculator {

    @Autowired
    private PaymentManager paymentManager;

    @Override
    public Receipt consider(Deal deal) {
        Date dateStartDeal = deal.getDate();
        Date dateEndDeal = new Date();
        long totalTime = (dateEndDeal.getTime() - dateStartDeal.getTime());
        long totalDays = ((((totalTime / 1000) / 60) / 60) / 24);

        long totalPrice = totalDays * deal.getCar().getRate().getCost();

        return paymentManager.getReceipt(deal, dateStartDeal, dateEndDeal, totalPrice);
    }
}
