package carsharing.web.dto;

import carsharing.dao.model.Car;
import lombok.Data;

import java.util.List;

@Data
public class OwnerDTO {
    private long id;

    private String name;

    private String surname;

    private String passport_number;

    private String phone_number;

    private String email;

    private String password;

    private int level;

    private List<Car> cars;
}
