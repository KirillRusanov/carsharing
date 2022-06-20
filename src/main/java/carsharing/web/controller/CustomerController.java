package carsharing.web.controller;

import carsharing.dao.model.Customer;
import carsharing.dao.model.Payment;
import carsharing.service.CustomerService;
import carsharing.web.dto.CardDTO;
import carsharing.web.dto.CustomerAuthenticationDTO;
import carsharing.web.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAnyRole('CUSTOMER', 'SPECIALIST', 'ADMIN')")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/profile")
    public String getProfile(Authentication authentication, Model model) {
        if (authentication == null) {
            model.addAttribute("customer", new CustomerAuthenticationDTO());
            return "login";
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        CustomerDTO customerDTO = customerService.getCustomerDtoByEmail(userDetails.getUsername());
        model.addAttribute("customer", customerDTO);
        model.addAttribute("payment", new Payment());
        return "profile";
    }

    @PostMapping(value = "/profile/update")
    public String updateProfile(@ModelAttribute CustomerDTO customer) {
        customerService.updateCustomerProfile(customer);
        return "redirect:/profile";
    }

    @PostMapping(value = "/profile/deposit")
    public String deposit(Authentication authentication, @ModelAttribute Payment payment, Model model) {
        if (authentication == null) {
            model.addAttribute("customer", new CustomerAuthenticationDTO());
            return "login";
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        customerService.depositToAccount(payment, userDetails.getUsername());
        return "redirect:/profile";
    }
}
