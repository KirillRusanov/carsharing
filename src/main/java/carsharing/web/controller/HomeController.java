package carsharing.web.controller;

import carsharing.service.CustomerService;
import carsharing.service.DealService;
import carsharing.web.dto.CustomerAuthenticationDTO;
import carsharing.web.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @Autowired
    private DealService dealService;
    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/index")
    public String getHomePage() {
        return "index";
    }

    @GetMapping(value = "/m-panel")
    public String getPanel(Authentication authentication, Model model) {
        if (authentication == null) {
            model.addAttribute("customer", new CustomerAuthenticationDTO());
            return "login";
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        CustomerDTO customerDTO = customerService.getCustomerDtoByEmail(userDetails.getUsername());
        model.addAttribute("deals", dealService.getUserDeals(customerDTO.getId()));
        model.addAttribute("customer", customerDTO);
        return "panel";
    }
    @GetMapping(value = "/partnership")
    public String getPartnershipPanel(Authentication authentication, Model model) {
        if (authentication == null) {
            model.addAttribute("customer", new CustomerAuthenticationDTO());
            return "login";
        }
        return "partnership";
    }
}
