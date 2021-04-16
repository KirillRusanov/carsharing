package carsharing.web.controller;

import carsharing.service.CarService;
import carsharing.web.dto.CarDTO;
import carsharing.web.mapper.CarMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/car")
public class CarController {

    @Autowired
    private CarService carService;

    private CarMapper carMapper = Mappers.getMapper(CarMapper.class);

    @GetMapping(value = "/own/list")
    public List<CarDTO> getCarList() {
        return carMapper.convertToDTO(carService.getAll());
    }

    @GetMapping(value = "/{id}")
    public CarDTO getCarById(@PathVariable("id") Long id) {
        return carMapper.convertToDTO(carService.getById(id));
    }

    @GetMapping(value = "/available")
    public List<CarDTO> getAvailableCars() {
        return carMapper.convertToDTO(carService.getAvailableCars());
    }
}
