package carsharing.web.dto;

import lombok.Data;

@Data
public class CustomerDTO {

    private long id;

    private String name;

    private String surname;

    private String passport_number;

    private String phone_number;
}
