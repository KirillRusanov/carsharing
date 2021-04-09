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

    private List<Car> cars;
}
