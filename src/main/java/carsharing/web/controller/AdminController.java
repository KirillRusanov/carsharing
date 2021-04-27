package carsharing.web.controller;

import carsharing.service.CustomerService;
import carsharing.service.DealService;
import carsharing.service.RateService;
import carsharing.service.SpecialistService;
import carsharing.web.dto.CustomerDTO;
import carsharing.web.dto.RateDTO;
import carsharing.web.dto.SpecialistDTO;
import carsharing.web.mapper.CustomerMapper;
import carsharing.web.mapper.RateMapper;
import carsharing.web.mapper.SpecialistMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin-panel")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private SpecialistService specialistService;
    @Autowired
    private DealService dealService;
    @Autowired
    private RateService rateService;
    @Autowired
    private CustomerService customerService;


    private SpecialistMapper specialistMapper = Mappers.getMapper(SpecialistMapper.class);
    private RateMapper rateMapper = Mappers.getMapper(RateMapper.class);
    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @GetMapping(value = "/specialists-list")
    public List<SpecialistDTO> getSpecialistList() {
        return specialistMapper.convertToDTO(specialistService.getAll());
    }

    @GetMapping(value = "/customers-list")
    public List<CustomerDTO> getCustomerList()  {
        return customerMapper.convertToDTO(customerService.getAll());
    }


    @GetMapping(value = "/rates/{id}/delete")
    public void deleteRate(@PathVariable("id") Long id) {
        rateService.delete(id);
    }

    @PostMapping(value = "/rates/create", produces = "application/json", consumes="application/json")
    public RateDTO createRate(@RequestBody RateDTO rateDTO) {
        rateService.save(rateMapper.convertToEntity(rateDTO));
        return rateDTO;
    }

    @PostMapping(value = "/rates/edit", produces = "application/json", consumes = "application/json")
    public RateDTO updateRate(@RequestBody RateDTO rateDTO) {
        rateService.save(rateMapper.convertToEntity(rateDTO));
        return rateDTO;
    }


    @GetMapping(value = "/deals/{id}/delete")
    public void deleteDealById(@PathVariable("id") Long id) {
        dealService.delete(id);
    }

    @GetMapping(value = "/customers/{id}/delete")
    public void deleteCustomerById(@PathVariable("id") Long id) {
        customerService.delete(id);
    }


    @GetMapping(value = "/specialists/{id}/delete")
    public void deleteSpecialistById(@PathVariable("id") Long id) {
        specialistService.delete(id);
    }

    @PostMapping(value = "/specialist/add", produces = "application/json", consumes="application/json")
    public SpecialistDTO addSpecialist(@RequestBody SpecialistDTO specialistDTO) {
        specialistService.save(specialistMapper.convertToEntity(specialistDTO));
        return specialistDTO;
    }

}
