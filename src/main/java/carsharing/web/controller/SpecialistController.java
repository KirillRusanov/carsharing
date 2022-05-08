package carsharing.web.controller;

import carsharing.dao.model.DealStatus;
import carsharing.service.CarService;
import carsharing.service.CustomerService;
import carsharing.service.DealService;
import carsharing.web.dto.CarDTO;
import carsharing.web.dto.CustomerDTO;
import carsharing.web.dto.DealDTO;
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

    @GetMapping(value = "/customers/{id}")
    public CustomerDTO getCustomerById(@PathVariable("id") Long id) {
        return customerService.findById(id);
    }

    @PostMapping(value = "/customers/edit", produces = "application/json", consumes = "application/json")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.save(customerDTO);
        return customerDTO;
    }



    @GetMapping(value = "/deals/{id}")
    public DealDTO getDealById(@PathVariable("id") Long id) {
        return dealService.getById(id);
    }

    @GetMapping(value = "/deals")
    public List<DealDTO> getAllDeals() {
        return dealService.getAll();
    }

    @GetMapping(value = "/deals-filter")
    public List<DealDTO> getDealsByStatus(@RequestParam("status") DealStatus status) {
        return dealService.getDealsByStatus(status);
    }

    @PostMapping(value = "/deals/edit", produces = "application/json", consumes = "application/json")
    public DealDTO updateDeal(@RequestBody DealDTO dealDTO) {
        dealService.save(dealDTO);
        return dealDTO;
    }



    @GetMapping(value = "/cars/list")
    public List<CarDTO> getCarList() {
        return carService.getAll();
    }

    @GetMapping(value = "/cars/{id}/delete")
    public void deleteCar(@PathVariable("id") Long id) {
        carService.delete(id);
    }

    @PostMapping(value = "/cars/add", produces = "application/json", consumes="application/json")
    public CarDTO addCar(@RequestBody CarDTO carDTO) {
        carService.save(carDTO);
        return carDTO;
    }

    @PostMapping(value = "/cars/edit", produces = "application/json", consumes = "application/json")
    public CarDTO updateCar(@RequestBody CarDTO carDTO) {
        carService.save(carDTO);
        return carDTO;
    }
}
