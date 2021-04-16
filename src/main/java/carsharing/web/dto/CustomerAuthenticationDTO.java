package carsharing.web.dto;

import lombok.Data;

@Data
public class CustomerAuthenticationDTO extends CustomerDTO {

    private String name;

    private String surname;

    private String passport_number;

    private String license_number;

    private String phone_number;

    private String email;

    private String password;
}
