package carsharing.web.controller;

import carsharing.dao.model.Customer;
import carsharing.service.CustomerService;
import carsharing.service.SpecialistService;
import carsharing.web.dto.CustomerDTO;
import carsharing.web.dto.SpecialistDTO;
import carsharing.web.mapper.CustomerMapper;
import carsharing.web.mapper.SpecialistMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private SpecialistService specialistService;

    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    private SpecialistMapper specialistMapper = Mappers.getMapper(SpecialistMapper.class);

    @GetMapping(value = "/profile")
    public CustomerDTO getProfile(@AuthenticationPrincipal Customer customerDetails) {
        return customerMapper.convertToDTO(customerDetails);
    }

    @GetMapping(value = "/profile/delete")
    public void deleteProfile(@AuthenticationPrincipal Customer customerDetails) {
        customerService.delete(customerDetails.getId());
    }

    @GetMapping(value = "/specialist/{id}")
    public SpecialistDTO getSpecialistById(@PathVariable("id") Long id) {
        return specialistMapper.convertToDTO(specialistService.findById(id));
    }
}
