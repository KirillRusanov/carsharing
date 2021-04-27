package carsharing.web.controller;

import carsharing.dao.model.*;
import carsharing.service.*;
import carsharing.service.exception.DealPaymentException;
import carsharing.web.dto.DealDTO;
import carsharing.web.dto.Receipt;
import carsharing.web.mapper.DealMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/deal")
@PreAuthorize("hasAnyRole('CUSTOMER', 'SPECIALIST', 'ADMIN')")
public class DealController {

    @Autowired
    private DealService dealService;
    @Autowired
    private CarService carService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DealManager dealManager;

    private DealMapper dealMapper = Mappers.getMapper(DealMapper.class);

    @PostMapping(value = "/start", produces = "application/json", consumes = "application/json")
    public DealDTO startDeal(@RequestParam("car") Long carId, @AuthenticationPrincipal Customer customerDetails, @RequestBody DealDTO dealDTO) {
        Car rentedCar = carService.findById(carId);
        if (carId != null) {
            if(dealDTO.getDescription() != null) {
                if (customerDetails.is_verified()) {
                    if (rentedCar.getCar_status().equals(CarStatus.AVAILABLE)) {
                        rentedCar.setCar_status(CarStatus.BUSY);
                        dealDTO.setCar(rentedCar);
                        dealDTO.setCustomer(customerService.getCustomerByEmail(customerDetails.getUsername()));
                        carService.save(rentedCar);
                        dealService.openDeal(dealMapper.convertToEntity(dealDTO));
                        return dealDTO;
                    }
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deal rejected");
    }

    @PostMapping(value = "/close", produces = "application/json", consumes = "application/json")
    public Receipt closeDeal(@RequestParam("car") Long carId, @AuthenticationPrincipal Customer customerDetails) {
        if (carId != null) {
            List<Deal> deals = customerDetails.getDeals();
            Car rentedCar = carService.findById(carId);
            for(Deal deal : deals) {
                try {
                    if (deal.getStatus().equals(DealStatus.ACTIVE)) {
                        Receipt receipt = dealManager.serve(deal);
                        rentedCar.setCar_status(CarStatus.AVAILABLE);
                        deal.setEndDate(LocalDateTime.now());
                        dealService.closeDeal(deal);
                        carService.save(rentedCar);
                        return receipt;
                    }
                } catch (DealPaymentException ex) {
                    throw new ResponseStatusException(HttpStatus.PAYMENT_REQUIRED, "Payment failed");
                } catch (ClassNotFoundException e) {
                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Type of rates does not exist");
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect data. Closing the deal was denied");
    }
}
