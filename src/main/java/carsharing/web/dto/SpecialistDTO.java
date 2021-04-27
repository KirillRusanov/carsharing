package carsharing.web.dto;

import carsharing.dao.model.Customer;
import lombok.Data;

import java.util.List;

@Data
public class SpecialistDTO {
    private long id;

    private String email;

    private String name;

    private String phone_number;

    private List<Customer> customers;
}
