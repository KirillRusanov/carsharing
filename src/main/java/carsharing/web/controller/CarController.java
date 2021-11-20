package carsharing.web.controller;

import carsharing.dao.model.Customer;
import carsharing.service.CarService;
import carsharing.service.remoteControl.RemoteCarControlService;
import carsharing.web.dto.CarDTO;
import carsharing.web.dto.ConditionCar;
import carsharing.web.mapper.CarMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private RemoteCarControlService remoteCarControlService;

    private CarMapper carMapper = Mappers.getMapper(CarMapper.class);

    @GetMapping(value = "/my-list")
    public List<CarDTO> getOwnCarList(@AuthenticationPrincipal Customer customerDetails) {
        return carMapper.convertToDTO(customerDetails.getCars());
    }

    @GetMapping(value = "/{id}")
    public CarDTO getCarById(@PathVariable("id") Long id) {
        return carMapper.convertToDTO(carService.findById(id));
    }

    @GetMapping(value = "/available")
    public ModelAndView getAvailableCars(ModelAndView model) {
        model.addObject("cars", carMapper.convertToDTO(carService.getAvailableCars()));
        model.setViewName("availableCar");
        return model;
    }

    @GetMapping(value = "/{id}/control-panel")
    public ConditionCar getAllIndicators(@PathVariable("id") Long id) {
        return remoteCarControlService.getAllIndicators(carService.findById(id));
    }

    @GetMapping(value = "/{id}/control-panel/open")
    public void openDoors(@PathVariable("id") Long id) {
        remoteCarControlService.openDoors(carService.findById(id));
    }

    @GetMapping(value = "/{id}/control-panel/close")
    public void closeDoors(@PathVariable("id") Long id) {
        remoteCarControlService.closeDoors(carService.findById(id));
    }

    @GetMapping(value = "/{id}/control-panel/turnOn")
    public void turnOnCar(@PathVariable("id") Long id) {
        remoteCarControlService.turnOnEngine(carService.findById(id));
    }

    @GetMapping(value = "/{id}/control-panel/turnOf")
    public void turnOfCar(@PathVariable("id") Long id) {
        remoteCarControlService.turnOfEngine(carService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{id}/control-panel/lock")
    public void lockManualControl(@PathVariable("id") Long id) {
        remoteCarControlService.lockManualControl(carService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value = "/{id}/control-panel/unlock")
    public void unlockManualControl(@PathVariable("id") Long id) {
        remoteCarControlService.unlockManualControl(carService.findById(id));
    }
}
