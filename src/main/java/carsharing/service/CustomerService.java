package carsharing.service;

import carsharing.dao.model.Customer;
import carsharing.dao.repository.CustomerRepository;
import carsharing.dao.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    public BCryptPasswordEncoder passwordEncoder;


    public ArrayList<Customer> getAll() {
        return (ArrayList<Customer>) customerRepository.findAll();
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id);
    }

    public boolean delete(Long id) {
        if (customerRepository.findById(id) != null) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void save(Customer customer) {
        customer.setRoles(Collections.singleton(roleRepository.findRoleByName("ROLE_USER")));
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.create(customer);
    }

    public void update(Customer entity) {
        customerRepository.update(entity);
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
