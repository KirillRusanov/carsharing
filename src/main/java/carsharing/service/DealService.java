package carsharing.service;

import carsharing.dao.model.Deal;
import carsharing.dao.model.DealStatus;
import carsharing.dao.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DealService {

    @Autowired
    private DealRepository dealRepository;

    public ArrayList<Deal> getAll() {
        return (ArrayList<Deal>) dealRepository.findAll();
    }

    public Deal getById(Long id) {
        return dealRepository.findById(id);
    }

    public void delete(Long id) {
        dealRepository.deleteById(id);
    }

    public void create(Deal entity) {
        dealRepository.create(entity);
    }

    public void update(Deal entity) {
        dealRepository.update(entity);
    }

    public List<Deal> getDealsByStatus(DealStatus dealStatus) {
        return dealRepository.getDealsByStatus(dealStatus);
    }
}
