package carsharing.web.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerDTO {

    private long id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private String passport_number;

    private String license_number;

    private String phone_number;

    private BigDecimal balance;
}
