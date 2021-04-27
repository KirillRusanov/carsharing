package carsharing.web.dto;

import carsharing.dao.model.RateType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Receipt {

    private long orderNumber;

    private String transactionNumber;

    private String rateName;

    private RateType rateType;

    private long totalPrice;

    private String car;

    private LocalDateTime dateStartDeal;

    private LocalDateTime dateEndDeal;


}
