package carsharing.web.dto;

import lombok.Data;

@Data
public class CustomerAuthenticationDTO extends CustomerDTO {

    private String email;

    private String password;
}
