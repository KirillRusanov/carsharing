package carsharing.web.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private long id;

    private String email;

    private String password;

    private String name;

    private String surname;

    private String passportNumber;

    private String licenseNumber;

    private String phoneNumber;

    private BigDecimal balance;
}
