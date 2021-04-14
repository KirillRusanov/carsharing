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

    @GetMapping(value = "/list")
    public List<CarDTO> getCarList() {
        return carMapper.convertToDTO(carService.getAll());
    }

    @GetMapping(value = "/get/{id}")
    public CarDTO getCarById(@PathVariable("id") Long id) {
        return carMapper.convertToDTO(carService.getById(id));
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteCar(@PathVariable("id") Long id) {
        carService.delete(id);
        return "Car deleted - " + id;
    }

    @PostMapping(value = "/create", produces = "application/json", consumes="application/json")
    public String createCar(@RequestBody CarDTO carDTO) {
        carService.create(carMapper.convertToEntity(carDTO));
        return "Car created";
    }

    @PostMapping(value = "/edit", produces = "application/json", consumes = "application/json")
    public String updateCar(@RequestBody CarDTO carDTO) {
        carService.update(carMapper.convertToEntity(carDTO));
        return "Car updated";
    }

    @GetMapping(value = "/cars")
    public List<CarDTO> getAvailableCars() {
        return carMapper.convertToDTO(carService.getAvailableCars());
    }
}
