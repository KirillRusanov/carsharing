package carsharing.service;

import carsharing.dao.model.Specialist;
import carsharing.dao.repository.SpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SpecialistService {

    @Autowired
    private SpecialistRepository specialistRepository;

    public ArrayList<Specialist> getAll() {
        return (ArrayList<Specialist>) specialistRepository.findAll();
    }

    public Specialist findById(Long id) {
        return specialistRepository.findById(id);
    }

    public void delete(Long id) {
        specialistRepository.delete(id);
    }

    public void save(Specialist entity) {
        specialistRepository.saveOrUpdate(entity);
    }
}
