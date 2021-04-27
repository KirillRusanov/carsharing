package carsharing.web.dto;

import carsharing.dao.model.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class CardDTO {

    private long id;

    private String number;

    private String code;

    private Date term;

    @JsonIgnore
    private Customer customer;
}
