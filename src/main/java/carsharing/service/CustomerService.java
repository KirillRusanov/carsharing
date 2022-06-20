package carsharing.service;

import carsharing.dao.model.Customer;
import carsharing.dao.model.Payment;
import carsharing.dao.repository.CustomerRepository;
import carsharing.service.exception.ServerNotFoundException;
import carsharing.web.dto.CustomerDTO;
import carsharing.web.mapper.CustomerMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);


    public List<CustomerDTO> getAll() {
        return customerMapper.convertToDTO(Streamable.of(customerRepository.findAll()).toList());
    }


    protected Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ServerNotFoundException("Customer with this ID not found!"));
    }

    public CustomerDTO findById(Long id) {
        return customerMapper.convertToDTO(getById(id));
    }

    public void delete(Long id) {
        if (customerRepository.findById(id).isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new ServerNotFoundException("Failed to delete customer. Customer with this ID not found!");
        }
    }

    public void save(CustomerDTO customer) {
        customerRepository.save(customerMapper.convertToEntity(customer));
    }

    public void updateCustomerProfile(CustomerDTO customerDto) {
        Customer loadedCustomer = getById(customerDto.getId());
        loadedCustomer.setPassportNumber(customerDto.getPassportNumber());
        loadedCustomer.setLicenseNumber(customerDto.getLicenseNumber());
        save(loadedCustomer);
    }

    public void depositToAccount(Payment payment, String username) {
        Customer customer = getCustomerByEmail(username);
        customer.setBalance(customer.getBalance().add(payment.getAmount()));
        save(customer);
    }

    protected void save(Customer customer) {
        customerRepository.save(customer);
    }


    Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public CustomerDTO getCustomerDtoByEmail(String email) {
        return customerMapper.convertToDTO(customerRepository.findByEmail(email));
    }

    public CustomerDTO getCustomerDtoByPhone(String phoneNumber) {
        return customerMapper.convertToDTO(customerRepository.findByPhoneNumber(phoneNumber));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = getCustomerByEmail(username);
        if(customer == null) throw new ServerNotFoundException("Customer not found!");
        return customer;
    }
}
