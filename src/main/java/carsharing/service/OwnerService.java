package carsharing.service;

import carsharing.dao.model.Owner;
import carsharing.dao.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    public ArrayList<Owner> getAll() {
        return (ArrayList<Owner>) ownerRepository.findAll();
    }

    public Owner getById(Long id) {
        return ownerRepository.findById(id);
    }

    public void delete(Long id) {
        ownerRepository.deleteById(id);
    }

    public void update(Owner entity) {
        ownerRepository.update(entity);
    }
}
