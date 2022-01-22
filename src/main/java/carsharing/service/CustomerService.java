package carsharing.service;

import carsharing.dao.model.Customer;
import carsharing.dao.repository.CustomerRepository;
import carsharing.web.dto.CustomerDTO;
import carsharing.web.mapper.CustomerMapper;
import org.mapstruct.factory.Mappers;
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

    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);


    public ArrayList<CustomerDTO> getAll() {
        return (ArrayList<CustomerDTO>) customerMapper.convertToDTO(customerRepository.findAll());
    }

    public CustomerDTO findById(Long id) {
        return customerMapper.convertToDTO(customerRepository.findById(id));
    }

    public boolean delete(Long id) {
        if (customerRepository.findById(id) != null) {
            customerRepository.delete(id);
            return true;
        }
        return false;
    }

    public void save(CustomerDTO customer) {
        customerRepository.saveOrUpdate(customerMapper.convertToEntity(customer));
    }

    protected void save(Customer customer) {
        customerRepository.saveOrUpdate(customer);
    }


    protected Customer getCustomerByEmail(String email) {
        return customerRepository.getCustomerByEmail(email);
    }

    public CustomerDTO getCustomerDTOByEmail(String email) {
        return customerMapper.convertToDTO(customerRepository.getCustomerByEmail(email));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = getCustomerByEmail(username);
        if(customer == null) throw new UsernameNotFoundException("Customer not found");
        return customer;
    }
}
