package carsharing.service.remoteControl;

import carsharing.dao.model.Car;
import org.springframework.stereotype.Component;

@Component
public interface RemoteSensor<T> {

    T getIndicator(Car car);

}
