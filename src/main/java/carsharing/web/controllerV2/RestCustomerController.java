package carsharing.web.controllerV2;

import carsharing.dao.model.Customer;
import carsharing.service.CustomerService;
import carsharing.service.SpecialistService;
import carsharing.web.dto.CustomerDTO;
import carsharing.web.dto.SpecialistDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/rest/customer")
@PreAuthorize("hasAnyRole('CUSTOMER', 'SPECIALIST', 'ADMIN')")
public class RestCustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private SpecialistService specialistService;

    @GetMapping(value = "/profile")
    public CustomerDTO getProfile(@AuthenticationPrincipal CustomerDTO customerDetails) {
        return customerDetails;
    }

    @GetMapping(value = "/profile/delete")
    public void deleteProfile(@AuthenticationPrincipal Customer customerDetails) {
        customerService.delete(customerDetails.getId());
    }

    @GetMapping(value = "/specialist/{id}")
    public SpecialistDTO getSpecialistById(@PathVariable("id") Long id) {
        return specialistService.findById(id);
    }
}
