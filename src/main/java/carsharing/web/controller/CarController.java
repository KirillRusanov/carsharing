package carsharing.web.controller;

import carsharing.dao.model.Customer;
import carsharing.service.CarService;
import carsharing.web.dto.CarDTO;
import carsharing.web.mapper.CarMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cars")
public class CarController {

    @Autowired
    private CarService carService;

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
    public List<CarDTO> getAvailableCars() {
        return carMapper.convertToDTO(carService.getAvailableCars());
    }
}
