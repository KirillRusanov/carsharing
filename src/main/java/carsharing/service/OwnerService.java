package carsharing.service;

import carsharing.dao.model.Owner;
import carsharing.dao.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public ArrayList<Owner> getAll() {
        return (ArrayList<Owner>) ownerRepository.findAll();
    }

    public Owner getById(Long id) {
        return ownerRepository.findById(id);
    }

    public void delete(Long id) {
        ownerRepository.deleteById(id);
    }

    public void create(Owner entity) {
        ownerRepository.create(entity);
    }

    public void update(Owner entity) {
        ownerRepository.update(entity);
    }
}
