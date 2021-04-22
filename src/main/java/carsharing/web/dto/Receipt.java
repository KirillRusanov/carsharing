package carsharing.web.dto;

import carsharing.dao.model.RateType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Receipt {

    private long orderNumber;

    private String transactionNumber;

    private String rateName;

    private RateType rateType;

    private long totalPrice;

    private String car;

    private Date dateStartDeal;

    private Date dateEndDeal;


}
