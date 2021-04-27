package carsharing.service;

import carsharing.dao.model.Customer;
import carsharing.dao.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;


    public ArrayList<Customer> getAll() {
        return (ArrayList<Customer>) customerRepository.findAll();
    }

    public Customer findById(Long id) {
        return customerRepository.findById(id);
    }

    public boolean delete(Long id) {
        if (customerRepository.findById(id) != null) {
            customerRepository.delete(id);
            return true;
        }
        return false;
    }

    public void save(Customer customer) {
        customerRepository.saveOrUpdate(customer);
    }


    public Customer getCustomerByEmail(String email) {
        return customerRepository.getCustomerByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = getCustomerByEmail(username);
        if(customer == null) throw new UsernameNotFoundException("Customer not found");
        return customer;
    }
}
