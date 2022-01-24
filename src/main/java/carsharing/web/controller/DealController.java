package carsharing.web.controller;

import carsharing.service.CustomerService;
import carsharing.service.DealService;
import carsharing.service.exception.DealPaymentException;
import carsharing.web.dto.CustomerAuthenticationDTO;
import carsharing.web.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("api/deal")
@PreAuthorize("hasAnyRole('CUSTOMER', 'SPECIALIST', 'ADMIN')")
public class DealController {

    @Autowired
    private DealService dealService;
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/start")
    public ModelAndView startDeal(Authentication authentication, ModelAndView model, @RequestParam("carId") Long carId) {
        if (authentication == null) {
            model.addObject("customer", new CustomerAuthenticationDTO());
            model.setViewName("login");
            return model;
        }
        if (carId != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            CustomerDTO customerDTO = customerService.getCustomerDTOByEmail(userDetails.getUsername());
            dealService.openDeal(userDetails.getUsername(), carId);
            model.setViewName("panel");
            model.addObject("deals", dealService.getUserDeals(customerDTO.getId()));
            model.addObject("customer", customerDTO);
            return model;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect data. Opening the deal was denied");
    }

    @PostMapping(value = "/close")
    public ModelAndView closeDeal(Authentication authentication, ModelAndView model, @RequestParam("dealId") Long dealId) {
        if (authentication == null) {
            model.addObject("customer", new CustomerAuthenticationDTO());
            model.setViewName("login");
            return model;
        }
        if (dealId != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            try {
                // TODO PDF Reader and show receipts
                dealService.closeDeal(userDetails.getUsername(), dealId);
                CustomerDTO customerDTO = customerService.getCustomerDTOByEmail(userDetails.getUsername());
                model.setViewName("panel");
                model.addObject("deals", dealService.getUserDeals(customerDTO.getId()));
                model.addObject("customer", customerDTO);
                return model;
            } catch (DealPaymentException ex) {
                throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, ex.getMessage());
            } catch (ClassNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Type of rates does not exist");
            }

        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect data. Closing the deal was denied");
    }
}
