package carsharing.web.dto;

import carsharing.dao.model.Car;
import carsharing.dao.model.Customer;
import carsharing.dao.model.DealStatus;
import carsharing.dao.model.Rate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class DealDTO {
    private long id;

    private DealStatus status;

    private Date date;

    private String description;

    @JsonIgnore
    private Car car_id;

    @JsonIgnore
    private Customer customer_id;
}
