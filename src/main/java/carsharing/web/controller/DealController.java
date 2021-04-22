package carsharing.web.controller;

import carsharing.dao.model.*;
import carsharing.service.*;
import carsharing.web.dto.DealDTO;
import carsharing.web.dto.Receipt;
import carsharing.web.mapper.DealMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/deal")
public class DealController {

    @Autowired
    private DealService dealService;
    @Autowired
    private CarService carService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PaymentManager paymentManager;

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
                if(deal.getStatus().equals(DealStatus.ACTIVE)) {
                    Receipt receipt = paymentManager.serve(deal);
                    rentedCar.setCar_status(CarStatus.AVAILABLE);
                    dealService.closeDeal(deal);
                    carService.save(rentedCar);
                    return receipt;
                }
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Closing the deal was denied");
    }
}
