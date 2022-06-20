package carsharing.web.dto;

import carsharing.dao.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CarDTO {

    private long id;

    private CarType type;

    private CarStatus carStatus;

    private String brand;

    private int fuel;

    @JsonIgnore
    private Rate rate;

    @JsonIgnore
    private Customer customer;

    private List<Deal> deals;

    private BigDecimal posX;

    private BigDecimal posY;

    private DoorStatus doorStatus;
}
