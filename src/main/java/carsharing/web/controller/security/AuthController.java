package carsharing.web.controller.security;

import carsharing.configuration.utils.CookiesConfigurer;
import carsharing.configuration.utils.JwtUtils;
import carsharing.dao.model.Customer;
import carsharing.service.CustomerService;
import carsharing.service.RoleService;
import carsharing.web.dto.CustomerAuthenticationDTO;
import carsharing.web.mapper.CustomerMapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;


@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CookiesConfigurer cookiesConfigurer;
    @Autowired
    private RoleService roleService;
    @Autowired
    public BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CustomerService customerService;

    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @PostMapping(value = "/signup", produces = "application/json", consumes="application/json")
    public CustomerAuthenticationDTO signUp(@RequestBody CustomerAuthenticationDTO customerAuthenticationDTO) {
        if (customerService.getCustomerByEmail(customerAuthenticationDTO.getEmail()) != null) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already taken.");
        }
        Customer customer = customerMapper.convertToEntity(customerAuthenticationDTO);
        customer.setRoles(Collections.singleton(roleService.findById(1L)));
        customer.setPassword(passwordEncoder.encode(customerAuthenticationDTO.getPassword()));
        customerService.save(customer);
        LOG.info("Customer " + customerAuthenticationDTO.getEmail() + " registered");
        return customerAuthenticationDTO;
    }

    @PostMapping(value = "/signin", produces = "application/json", consumes="application/json")
    public CustomerAuthenticationDTO singIn(@RequestBody CustomerAuthenticationDTO customerAuthenticationDTO,
                         HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(customerAuthenticationDTO.getEmail(), customerAuthenticationDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        response.addCookie(cookiesConfigurer.configureCookie(jwt));
        return customerAuthenticationDTO;
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        Arrays.stream(request
                .getCookies())
                .peek(a -> a.setMaxAge(0))
                .forEach(response::addCookie);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}

