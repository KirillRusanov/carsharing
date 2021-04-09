package carsharing.web.controller;

import carsharing.service.CustomerService;
import carsharing.web.dto.CustomerDTO;
import carsharing.web.mapper.CustomerMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    @GetMapping(value = "/list")
    public List<CustomerDTO> getCustomerList() {
        return customerMapper.convertToDTO(customerService.getAll());
    }

    @GetMapping(value = "/get/{id}")
    public CustomerDTO getCustomerById(@PathVariable("id") Long id) {
        return customerMapper.convertToDTO(customerService.getById(id));
    }
}
