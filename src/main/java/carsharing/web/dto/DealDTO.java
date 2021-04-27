package carsharing.web.dto;

import carsharing.dao.model.Car;
import carsharing.dao.model.Customer;
import carsharing.dao.model.DealStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DealDTO {
    private long id;

    private DealStatus status;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String description;

    @JsonIgnore
    private Car car;

    @JsonIgnore
    private Customer customer;
}
