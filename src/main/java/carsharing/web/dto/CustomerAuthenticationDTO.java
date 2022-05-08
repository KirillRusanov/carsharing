package carsharing.web.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAuthenticationDTO extends CustomerDTO {

    private String email;

    private String password;
}
