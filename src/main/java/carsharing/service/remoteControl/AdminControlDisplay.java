package carsharing.service.remoteControl;

import carsharing.dao.model.Car;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AdminControlDisplay extends ControlDisplay{

    private static final Logger LOG = LoggerFactory.getLogger(AdminControlDisplay.class);

    public void lockManualControl(Car car) {
        LOG.info("Car - " + car.getId() + " manual control disabled");
    }

    public void unlockManualControl(Car car) {
        LOG.info("Car - " + car.getId() + " manual control enabled");
    }
}
