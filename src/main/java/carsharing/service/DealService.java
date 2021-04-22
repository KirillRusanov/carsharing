package carsharing.service;

import carsharing.dao.model.Deal;
import carsharing.dao.model.DealStatus;
import carsharing.dao.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DealService {

    @Autowired
    private DealRepository dealRepository;

    public ArrayList<Deal> getAll() {
        return (ArrayList<Deal>) dealRepository.findAll();
    }

    public Deal findById(Long id) {
        return dealRepository.findById(id);
    }

    public void delete(Long id) {
        dealRepository.delete(id);
    }

    public void save(Deal entity) {
        dealRepository.saveOrUpdate(entity);
    }


    public void closeDeal(Deal deal) {
        deal.setStatus(DealStatus.FINISHED);
        save(deal);
    }

    public void openDeal(Deal deal) {
        deal.setDate(new Date());
        deal.setStatus(DealStatus.ACTIVE);
        save(deal);
    }

    public List<Deal> getDealsByStatus(DealStatus dealStatus) {
        return dealRepository.getDealsByStatus(dealStatus);
    }
}
