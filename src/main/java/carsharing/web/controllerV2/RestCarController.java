package carsharing.web.controllerV2;

import carsharing.service.CarService;
import carsharing.service.remoteControl.RemoteCarControlService;
import carsharing.web.dto.CarDTO;
import carsharing.web.dto.ConditionCar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("api/rest/cars")
public class RestCarController {

    @Autowired
    private CarService carService;
    @Autowired
    private RemoteCarControlService remoteCarControlService;

    @GetMapping(value = "/{id}")
    public CarDTO getCarById(@PathVariable("id") Long id) {
        return carService.findById(id);
    }

    @GetMapping(value = "/available")
    public ModelAndView getAvailableCars(ModelAndView model) {
        model.addObject("cars", carService.getAvailableCars());
        model.setViewName("availableCar");
        return model;
    }

    @GetMapping(value = "/{id}/control-panel")
    public ConditionCar getAllIndicators(@PathVariable("id") Long id) {
//        return remoteCarControlService.getAllIndicators(carService.findById(id));
        return ConditionCar.builder().speed(20).build();
    }

    @GetMapping(value = "/{id}/control-panel/open")
    public void openDoors(@PathVariable("id") Long id) {
//        remoteCarControlService.openDoors(carService.findById(id));
    }

    @GetMapping(value = "/{id}/control-panel/close")
    public void closeDoors(@PathVariable("id") Long id) {
//        remoteCarControlService.closeDoors(carService.findById(id));
    }

    @GetMapping(value = "/{id}/control-panel/turnOn")
    public void turnOnCar(@PathVariable("id") Long id) {
//        remoteCarControlService.turnOnEngine(carService.findById(id));
    }

    @GetMapping(value = "/{id}/control-panel/turnOf")
    public void turnOfCar(@PathVariable("id") Long id) {
//        remoteCarControlService.turnOfEngine(carService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{id}/control-panel/lock")
    public void lockManualControl(@PathVariable("id") Long id) {
//        remoteCarControlService.lockManualControl(carService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{id}/control-panel/unlock")
    public void unlockManualControl(@PathVariable("id") Long id) {
//        remoteCarControlService.unlockManualControl(carService.findById(id));
    }
}
