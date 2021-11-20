package carsharing.web.dto;

import carsharing.dao.model.Car;
import carsharing.dao.model.Customer;
import carsharing.dao.model.DealStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.TimeZone;

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

    public String getStartDateForView() {
        return startDate.format(DateTimeFormatter.ofPattern("uuuu - d MMM HH:mm:ss"));
    }

    public String getEndDateForView() {
        return endDate.format(DateTimeFormatter.ofPattern("uuuu - d MMM HH:mm:ss"));
    }
}
