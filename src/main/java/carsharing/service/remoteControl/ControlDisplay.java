package carsharing.service.remoteControl;

import carsharing.dao.model.Car;
import carsharing.web.dto.ConditionCar;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public abstract class ControlDisplay {

    @Autowired
    private FuelSensor fuelSensor;
    @Autowired
    private SpeedSensor speedSensor;
    @Autowired
    private GPSSensor gpsSensor;
    @Autowired
    private TemperatureSensor temperatureSensor;

    private static final Logger LOG = LoggerFactory.getLogger(ControlDisplay.class);

    public void openDoors(Car car) {
        LOG.info("Doors are open. Car number: " + car.getId());
    }

    public void closeDoors(Car car) {
        LOG.info("Doors are close. Car number: " + car.getId());
    }

    public void turnOnEngine(Car car) {
        LOG.info("Engine is running. Car number: " + car.getId());
    }

    public void turnOfEngine(Car car) {
        LOG.info("Engine off. Car number: " + car.getId());
    }

    public ConditionCar getAllIndicators(Car car) {
        return ConditionCar
                .builder()
                .fuel(fuelSensor.getIndicator(car))
                .gps(gpsSensor.getIndicator(car))
                .speed(speedSensor.getIndicator(car))
                .temperature(temperatureSensor.getIndicator(car))
                .build();
    }


}
