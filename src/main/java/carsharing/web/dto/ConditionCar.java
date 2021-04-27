package carsharing.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConditionCar {

    private String gps;

    private int fuel;

    private int speed;

    private int temperature;
}
