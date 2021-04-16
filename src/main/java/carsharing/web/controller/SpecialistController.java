package carsharing.web.controller;

import carsharing.dao.model.DealStatus;
import carsharing.service.CarService;
import carsharing.service.CustomerService;
import carsharing.service.DealService;
import carsharing.service.SpecialistService;
import carsharing.web.dto.CarDTO;
import carsharing.web.dto.CustomerDTO;
import carsharing.web.dto.DealDTO;
import carsharing.web.dto.SpecialistDTO;
import carsharing.web.mapper.CarMapper;
import carsharing.web.mapper.CustomerMapper;
import carsharing.web.mapper.DealMapper;
import carsharing.web.mapper.SpecialistMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/specialist")
public class SpecialistController {

    @Autowired
    private DealService dealService;
    @Autowired
    private CarService carService;
    @Autowired
    private SpecialistService specialistService;
    @Autowired
    private CustomerService customerService;

    private SpecialistMapper specialistMapper = Mappers.getMapper(SpecialistMapper.class);
    private CarMapper carMapper = Mappers.getMapper(CarMapper.class);
    private DealMapper dealMapper = Mappers.getMapper(DealMapper.class);
    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @GetMapping(value = "/{id}")
    public SpecialistDTO getSpecialistById(@PathVariable("id") Long id) {
        return specialistMapper.convertToDTO(specialistService.getById(id));
    }

    @GetMapping(value = "/customer/{id}")
    public CustomerDTO getCustomerById(@PathVariable("id") Long id) {
        return customerMapper.convertToDTO(customerService.getById(id));
    }

    @GetMapping(value = "/deal/{id}")
    public DealDTO getDealById(@PathVariable("id") Long id) {
        return dealMapper.convertToDTO(dealService.getById(id));
    }

    @GetMapping(value = "/deals")
    public List<DealDTO> getAllDeals() {
        return dealMapper.convertToDTO(dealService.getAll());
    }

    @GetMapping(value = "/deals-filter")
    public List<DealDTO> getDealsByStatus(@RequestParam("status") DealStatus status) {
        return dealMapper.convertToDTO(dealService.getDealsByStatus(status));
    }

    @PostMapping(value = "/deal/{id}/edit", produces = "application/json", consumes = "application/json")
    public String updateDeal(@PathVariable("id") Long id, @RequestBody DealDTO dealDTO) {
        dealService.update(dealMapper.convertToEntity(dealDTO));
        return "Deal updated";
    }

    @GetMapping(value = "/list")
    public List<CarDTO> getCarList() {
        return carMapper.convertToDTO(carService.getAll());
    }

    @GetMapping(value = "cars/{id}/remove")
    public String removeCar(@PathVariable("id") Long id) {
        carService.delete(id);
        return "Car deleted - " + id;
    }

    @PostMapping(value = "cars/add", produces = "application/json", consumes="application/json")
    public String addCar(@RequestBody CarDTO carDTO) {
        carService.create(carMapper.convertToEntity(carDTO));
        return "Car created";
    }

    @PostMapping(value = "cars/{id}/edit", produces = "application/json", consumes = "application/json")
    public String updateCar(@PathVariable("id") Long id, @RequestBody CarDTO carDTO) {
        carService.update(carMapper.convertToEntity(carDTO));
        return "Car updated";
    }
}
