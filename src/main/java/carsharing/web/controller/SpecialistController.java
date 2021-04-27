package carsharing.web.controller;

import carsharing.dao.model.DealStatus;
import carsharing.service.CarService;
import carsharing.service.CustomerService;
import carsharing.service.DealService;
import carsharing.web.dto.CarDTO;
import carsharing.web.dto.CustomerDTO;
import carsharing.web.dto.DealDTO;
import carsharing.web.mapper.CarMapper;
import carsharing.web.mapper.CustomerMapper;
import carsharing.web.mapper.DealMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/specialist-panel")
@PreAuthorize("hasAnyRole('SPECIALIST', 'ADMIN')")
public class SpecialistController {

    @Autowired
    private DealService dealService;
    @Autowired
    private CarService carService;
    @Autowired
    private CustomerService customerService;

    private CarMapper carMapper = Mappers.getMapper(CarMapper.class);
    private DealMapper dealMapper = Mappers.getMapper(DealMapper.class);
    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @GetMapping(value = "/customers/{id}")
    public CustomerDTO getCustomerById(@PathVariable("id") Long id) {
        return customerMapper.convertToDTO(customerService.findById(id));
    }

    @PostMapping(value = "/customers/edit", produces = "application/json", consumes = "application/json")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.save(customerMapper.convertToEntity(customerDTO));
        return customerDTO;
    }



    @GetMapping(value = "/deals/{id}")
    public DealDTO getDealById(@PathVariable("id") Long id) {
        return dealMapper.convertToDTO(dealService.findById(id));
    }

    @GetMapping(value = "/deals")
    public List<DealDTO> getAllDeals() {
        return dealMapper.convertToDTO(dealService.getAll());
    }

    @GetMapping(value = "/deals-filter")
    public List<DealDTO> getDealsByStatus(@RequestParam("status") DealStatus status) {
        return dealMapper.convertToDTO(dealService.getDealsByStatus(status));
    }

    @PostMapping(value = "/deals/edit", produces = "application/json", consumes = "application/json")
    public DealDTO updateDeal(@RequestBody DealDTO dealDTO) {
        dealService.save(dealMapper.convertToEntity(dealDTO));
        return dealDTO;
    }



    @GetMapping(value = "/cars/list")
    public List<CarDTO> getCarList() {
        return carMapper.convertToDTO(carService.getAll());
    }

    @GetMapping(value = "/cars/{id}/delete")
    public void deleteCar(@PathVariable("id") Long id) {
        carService.delete(id);
    }

    @PostMapping(value = "/cars/add", produces = "application/json", consumes="application/json")
    public CarDTO addCar(@RequestBody CarDTO carDTO) {
        carService.save(carMapper.convertToEntity(carDTO));
        return carDTO;
    }

    @PostMapping(value = "/cars/edit", produces = "application/json", consumes = "application/json")
    public CarDTO updateCar(@RequestBody CarDTO carDTO) {
        carService.save(carMapper.convertToEntity(carDTO));
        return carDTO;
    }
}
