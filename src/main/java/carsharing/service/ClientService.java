package carsharing.service;

import carsharing.dao.model.Client;
import carsharing.dao.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    public ArrayList<Client> getAll() {
        return (ArrayList<Client>) clientRepository.findAll();
    }

    public Client getById(Long id) {
        return clientRepository.findById(id);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public void update(Client entity) {
        clientRepository.update(entity);
    }
}
