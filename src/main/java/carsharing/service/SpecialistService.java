package carsharing.service;

import carsharing.dao.model.Deal;
import carsharing.dao.model.Specialist;
import carsharing.dao.repository.SpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpecialistService {

    @Autowired
    private SpecialistRepository specialistRepository;

    public ArrayList<Specialist> getAll() {
        return (ArrayList<Specialist>) specialistRepository.findAll();
    }

    public Specialist getById(Long id) {
        return specialistRepository.findById(id);
    }

    public void delete(Long id) {
        specialistRepository.deleteById(id);
    }

    public void create(Specialist entity) {
        specialistRepository.create(entity);
    }

    public void update(Specialist entity) {
        specialistRepository.update(entity);
    }

    public List<Deal> getActiveDeals() {
        return specialistRepository.getActiveDeals();
    }
}
