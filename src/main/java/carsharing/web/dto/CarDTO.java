package carsharing.web.dto;

import carsharing.dao.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class CarDTO {

    private long id;

    private CarType type;

    private CarStatus car_status;

    private String brand;

    private int fuel;

    @JsonIgnore
    private Rate rate;

    @JsonIgnore
    private Customer customer;

    private List<Deal> deals;

}
