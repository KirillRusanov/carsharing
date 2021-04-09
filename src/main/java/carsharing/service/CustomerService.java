package carsharing.service;

import carsharing.dao.model.Customer;
import carsharing.dao.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public ArrayList<Customer> getAll() {
        return (ArrayList<Customer>) customerRepository.findAll();
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id);
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    public void create(Customer entity) {
        customerRepository.create(entity);
    }

    public void update(Customer entity) {
        customerRepository.update(entity);
    }
}
