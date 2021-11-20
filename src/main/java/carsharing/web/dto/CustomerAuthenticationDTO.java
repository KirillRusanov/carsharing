package carsharing.web.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CustomerAuthenticationDTO extends CustomerDTO {

    private String email;

    private String password;
}
