package carsharing.service;

import carsharing.dao.model.*;
import carsharing.dao.repository.DealRepository;
import carsharing.service.exception.DealPaymentException;
import carsharing.web.dto.DealDTO;
import carsharing.web.dto.Receipt;
import carsharing.web.mapper.DealMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealService {

    @Autowired
    private DealRepository dealRepository;
    @Autowired
    private CarService carService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DealManager dealManager;

    private DealMapper dealMapper = Mappers.getMapper(DealMapper.class);

    public ArrayList<DealDTO> getAll() {
        return (ArrayList<DealDTO>) dealMapper.convertToDTO(dealRepository.findAll());
    }

    public DealDTO findById(Long id) {
        return dealMapper.convertToDTO(dealRepository.findById(id));
    }

    public void delete(Long id) {
        dealRepository.delete(id);
    }

    public void save(DealDTO deal) {
        dealRepository.saveOrUpdate(dealMapper.convertToEntity(deal));
    }

    private void save(Deal deal) {
        dealRepository.saveOrUpdate(deal);
    }

    public Receipt closeDeal(String userEmail, long dealId) throws ClassNotFoundException {
        Deal deal = dealMapper.convertToEntity(findById(dealId));
        if (deal.getStatus().equals(DealStatus.ACTIVE) && deal.getCustomer().getId() == (customerService.getCustomerByEmail(userEmail).getId())) {
            Receipt receipt = dealManager.serve(deal);
            deal.setEndDate(LocalDateTime.now());
            Car rentedCar = deal.getCar();
            rentedCar.setCarStatus(CarStatus.AVAILABLE);
            carService.save(rentedCar);
            deal.setStatus(DealStatus.FINISHED);
            save(deal);
            carService.save(rentedCar);
            return receipt;
        }
        throw new DealPaymentException("Deal already completed or you aren't the owner of the deal.");
    }

    public void openDeal(String userEmail, long carId) {
        Customer customer = customerService.getCustomerByEmail(userEmail);
        if (customer.isVerified()) {
            Car desiredCar = carService.findById(carId);
            if (desiredCar.getCarStatus().equals(CarStatus.AVAILABLE)) {
                desiredCar.setCarStatus(CarStatus.BUSY);
                carService.save(desiredCar);
                Deal deal = new Deal(DealStatus.ACTIVE, null, null, "test", desiredCar, customer);
                if (deal.getStartDate() == null || deal.getStartDate().isBefore(LocalDateTime.now())) {
                    deal.setStartDate(LocalDateTime.now());
                } // TODO get startDate and Description from form "Deal Starting"
                save(deal);
            }
        }
    }

    public List<DealDTO> getDealsByStatus(DealStatus dealStatus) {
        return dealMapper.convertToDTO(dealRepository.getDealsByStatus(dealStatus));
    }

    public List<DealDTO> getUserDeals(Long id) {
        return getAll()
                .stream()
                .filter(e -> e.getCustomer().getId() == id)
                .collect(Collectors.toList());
    }
}
