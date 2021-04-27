package carsharing.service.remoteControl;

import carsharing.dao.model.Car;
import carsharing.web.dto.ConditionCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoteCarControlService {

    @Autowired
    private AdminControlDisplay adminControlDisplay;

    @Autowired
    private CustomerControlDisplay customerControlDisplay;

    public void openDoors(Car car) {
        customerControlDisplay.openDoors(car);
    }

    public void closeDoors(Car car) {
        customerControlDisplay.closeDoors(car);
    }

    public void turnOnEngine(Car car) {
        customerControlDisplay.turnOnEngine(car);
    }

    public void turnOfEngine(Car car) {
        customerControlDisplay.turnOfEngine(car);
    }

    public ConditionCar getAllIndicators(Car car) {
        return customerControlDisplay.getAllIndicators(car);
    }

    public void lockManualControl(Car car) {
        adminControlDisplay.lockManualControl(car);
    }

    public void unlockManualControl(Car car) {
        adminControlDisplay.unlockManualControl(car);
    }
}
