package carsharing.service;

import carsharing.dao.model.*;
import carsharing.dao.repository.DealRepository;
import carsharing.service.documentService.pdf.receipt.DealReceiptGenerator;
import carsharing.service.exception.ServerNotFoundException;
import carsharing.service.exception.deal.DealClosingException;
import carsharing.service.exception.deal.DealOpeningException;
import carsharing.service.mail.MailService;
import carsharing.service.mail.MessageInfo;
import carsharing.web.dto.DealDTO;
import carsharing.web.dto.Receipt;
import carsharing.web.mapper.DealMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    @Autowired
    private DealReceiptGenerator receiptGenerator;
    @Autowired
    @Qualifier("actionService")
    private MailService mailService;

    private DealMapper dealMapper = Mappers.getMapper(DealMapper.class);

    public ArrayList<DealDTO> getAll() {
        return (ArrayList<DealDTO>) dealMapper.convertToDTO(Streamable.of(dealRepository.findAll()).toList());
    }

    protected Deal findById(Long id) {
        return dealRepository.findById(id).orElseThrow(() -> new ServerNotFoundException("Car with this ID not found!"));
    }

    public DealDTO getById(Long id) {
        return dealMapper.convertToDTO(findById(id));
    }

    public void delete(Long id) {
        dealRepository.deleteById(id);
    }

    public void save(DealDTO deal) {
        dealRepository.save(dealMapper.convertToEntity(deal));
    }

    private void save(Deal deal) {
        dealRepository.save(deal);
    }

    public DealDTO closeDeal(String userEmail, long dealId) {
        Deal deal = findById(dealId);
        if (deal.getStatus().equals(DealStatus.ACTIVE) && deal.getCustomer().getId() == (customerService.getCustomerByEmail(userEmail).getId())) {
            try {
                Car rentedCar = deal.getCar();
                processCarForClosingDeal(rentedCar);
                carService.save(rentedCar);

                Receipt receipt = dealManager.serve(deal);
                File receiptTempFile = receiptGenerator.createReceipt(receipt);
                processDealForClosing(deal, receiptTempFile);
                save(deal);
                return dealMapper.convertToDTO(deal);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new DealClosingException("Deal already completed or you aren't the owner of the deal.");
    }

    private void processDealForClosing(Deal deal, File receiptTempFile) throws IOException {
        byte[] receiptBytes = Files.readAllBytes(receiptTempFile.toPath());
        deal.setReceipt(receiptBytes);
        deal.setStatus(DealStatus.FINISHED);
        deal.setEndDate(LocalDateTime.now());
    }

    private void processCarForClosingDeal(Car rentedCar) {
        double randomPos = simulateMoving(-0.01000, 0.01000);
        rentedCar.setPosX(randomPos > 0 ? rentedCar.getPosX().add(new BigDecimal(randomPos)) : rentedCar.getPosX().subtract(new BigDecimal(randomPos)));
        rentedCar.setPosY(randomPos > 0 ? rentedCar.getPosY().subtract(new BigDecimal(randomPos)) : rentedCar.getPosY().add(new BigDecimal(randomPos)));
        rentedCar.setCarStatus(CarStatus.AVAILABLE);
    }

    private double simulateMoving(double min, double max) {
        Random r = new Random();
        return (r.nextInt((int)((max-min)*10+1))+min*10) / 10.0;
    }

    public void openDeal(String userEmail, long carId) {
        Customer customer = customerService.getCustomerByEmail(userEmail);
        if (customer.isVerified()) {
            Car desiredCar = carService.getById(carId);
            if (desiredCar.getCarStatus().equals(CarStatus.AVAILABLE)) {
                desiredCar.setCarStatus(CarStatus.BUSY);
                carService.save(desiredCar);
                Deal deal = new Deal(DealStatus.ACTIVE, null, null, "test", desiredCar, customer,null);
                if (deal.getStartDate() == null || deal.getStartDate().isBefore(LocalDateTime.now())) {
                    deal.setStartDate(LocalDateTime.now());
                } // TODO get startDate and Description from form "Deal Starting"
                save(deal);
            } else {
                throw new DealOpeningException("Car is not available");
            }
        } else {
            throw new DealOpeningException("Account is not verified");
        }
    }

    public List<DealDTO> getDealsByStatus(DealStatus dealStatus) {
        return dealMapper.convertToDTO(dealRepository.findAllByStatus(dealStatus));
    }

    private byte[] getReceiptByDealId(Long id) {
        return findById(id).getReceipt();
    }

    public void sendReceiptByEmail(String email, DealDTO deal) {
        try {
            MessageInfo messageInfo = configureMessageInfoForReceiptMailer(email, deal);
            mailService.sendMessage(messageInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MessageInfo configureMessageInfoForReceiptMailer(String email, DealDTO deal) throws IOException {
        File tempFile = File.createTempFile("receipt", ".pdf");
        Files.write(tempFile.toPath(), getReceiptByDealId(deal.getId()));
        return MessageInfo.builder()
                .emailTo(email)

                .nameFrom("Deal Service")
                .subject("Receipt of deal ??? " + deal.getId())
                .attachments(Collections.singletonList(tempFile))
                .messageHtml(getMessageForReceiptMailer())
                .build();
    }

    private String getMessageForReceiptMailer() {
        return "<h3>Dear client. Thank you for using our service!" +
                "<br>Your receipt for the transaction in PDF format is attached to the letter.</h3>" +
                "<br>-----------------------------------------------" +
                "<br><i color='black'>Please do not reply to this message, it was generated automatically.</i>";
    }

    public List<DealDTO> getUserDeals(Long id) {
        return dealMapper.convertToDTO(dealRepository.findAllByCustomer(customerService.getById(id)));
    }
}
