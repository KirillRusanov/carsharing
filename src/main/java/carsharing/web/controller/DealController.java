package carsharing.web.controller;

import carsharing.dao.model.DealStatus;
import carsharing.service.CustomerService;
import carsharing.service.DealService;
import carsharing.service.exception.CarsharingException;
import carsharing.web.dto.CustomerAuthenticationDTO;
import carsharing.web.dto.CustomerDTO;
import carsharing.web.dto.DealDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("api/deal")
public class DealController {

    @Autowired
    private DealService dealService;
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/start")
    public ModelAndView startDeal(@AuthenticationPrincipal UserDetails userDetails, ModelAndView model, @RequestParam("carId") Long carId) {
        if (userDetails == null) {
            model.addObject("customer", new CustomerAuthenticationDTO());
            model.setViewName("login");
            return model;
        }
        CustomerDTO customerDTO = customerService.getCustomerDtoByEmail(userDetails.getUsername());
        dealService.openDeal(userDetails.getUsername(), carId);
        model.setViewName("redirect:/m-panel");
        model.addObject("deals", dealService.getUserDeals(customerDTO.getId()));
        model.addObject("customer", customerDTO);
        return model;
    }

    @PostMapping(value = "/close")
    public ModelAndView closeDeal(@AuthenticationPrincipal UserDetails userDetails, ModelAndView model, @RequestParam("dealId") Long dealId) {
        if (userDetails == null) {
            model.addObject("customer", new CustomerAuthenticationDTO());
            model.setViewName("login");
            return model;
        }
        // TODO PDF Reader and show receipts
        DealDTO deal = dealService.closeDeal(userDetails.getUsername(), dealId);
        model.setViewName("receiptInfo");
        model.addObject("deal", deal);
        return model;
    }

    @PostMapping(value = "/sendReceipt")
    public ModelAndView sendReceipt(ModelAndView modelAndView,
            @AuthenticationPrincipal UserDetails userDetails, @RequestParam("dealId") Long dealId, @RequestParam("email") String email) {
        DealDTO deal = dealService.getById(dealId);
        if (isPersonalDeal(userDetails.getUsername(), deal) && deal.getStatus().equals(DealStatus.FINISHED)) {
            dealService.sendReceiptByEmail(email, deal);
            modelAndView.setViewName("receiptInfo");
            modelAndView.addObject("deal", deal);
        } else {
            throw new CarsharingException("Problem with sending");
        }
        return modelAndView;
    }

    @GetMapping(value = "/receipt")
    public ModelAndView getReceipt(@AuthenticationPrincipal UserDetails userDetails, ModelAndView model, @RequestParam("dealId") Long dealId) {
        if (userDetails == null) {
            model.addObject("customer", new CustomerAuthenticationDTO());
            model.setViewName("login");
            return model;
        }
        model.setViewName("receiptInfo");
        model.addObject("deal", dealService.getById(dealId));
        return model;
    }

    private boolean isPersonalDeal(String username, DealDTO deal) {
        return deal.getCustomer().getUsername().equals(username);
    }
}
