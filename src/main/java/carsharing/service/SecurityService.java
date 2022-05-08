package carsharing.service;

import carsharing.dao.model.Customer;
import carsharing.service.security.JwtTokenProvider;
import carsharing.web.dto.CustomerAuthenticationDTO;
import carsharing.web.dto.CustomerDTO;
import carsharing.web.dto.CustomerRegistrationDTO;
import carsharing.web.mapper.CustomerMapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;

@Service
public class SecurityService {

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CustomerService customerService;

    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    private static final Logger LOG = LoggerFactory.getLogger(SecurityService.class);

    public void registerUser(CustomerRegistrationDTO customerDTO) {
        Customer customer = customerMapper.convertToEntity(customerDTO);
        customer.setRoles(roleService.getDefaultUserRoles());
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        customer.setBalance(BigDecimal.ZERO);
        customerService.save(customer);
        LOG.info("Customer " + customerDTO.getEmail() + " registered");
    }

    public String authorizeUser(Authentication authentication, CustomerAuthenticationDTO customerAuthenticationDTO) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LOG.info("Client \"{}\" logged into the system", customerAuthenticationDTO.getEmail());
        return jwtTokenProvider.createToken(customerAuthenticationDTO.getEmail());
    }

}
