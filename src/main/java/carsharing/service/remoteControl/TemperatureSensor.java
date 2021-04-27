package carsharing.service.remoteControl;

import carsharing.dao.model.Car;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TemperatureSensor implements RemoteSensor<Integer> {
    @Override
    public Integer getIndicator(Car car) {
        return (new Random().nextInt(45));
    }
}
