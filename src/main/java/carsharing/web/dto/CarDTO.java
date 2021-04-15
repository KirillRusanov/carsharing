package carsharing.web.dto;

import carsharing.dao.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class CarDTO {

    private long id;

    private CarType car_type;

    private CarStatus status;

    private String brand;

    private int fuel;

    @JsonIgnore
    private Rate rate_id;

    @JsonIgnore
    private Customer customer_id;

    private List<Deal> deals;

}
