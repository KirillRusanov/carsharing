package carsharing.service;

import carsharing.dao.model.Rate;
import carsharing.dao.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    public ArrayList<Rate> getAll() {
        return (ArrayList<Rate>) rateRepository.findAll();
    }

    public Rate getById(Long id) {
        return rateRepository.findById(id);
    }

    public void delete(Long id) {
        rateRepository.deleteById(id);
    }

    public void create(Rate entity) {
        rateRepository.create(entity);
    }

    public void update(Rate entity) {
        rateRepository.update(entity);
    }

}
