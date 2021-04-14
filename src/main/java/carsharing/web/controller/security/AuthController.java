package carsharing.web.controller.security;

import carsharing.configuration.utils.JwtUtils;
import carsharing.dao.model.Customer;
import carsharing.service.CustomerService;
import carsharing.web.controller.payload.JwtResponse;
import carsharing.web.dto.CustomerDTO;
import carsharing.web.mapper.ClientMapper;
import carsharing.web.mapper.CustomerMapper;
import carsharing.web.mapper.OwnerMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JwtUtils jwtUtils;

    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);
    private OwnerMapper ownerMapper = Mappers.getMapper(OwnerMapper.class);
    private ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);

    @PostMapping(value = "/signup", produces = "application/json", consumes="application/json")
    public ResponseEntity<?> signUp(@RequestBody CustomerDTO customerDTO) {
        if (customerService.getCustomerByEmail(customerDTO.getEmail()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already taken!");
        }
        customerService.save(customerMapper.convertToEntity(customerDTO));
        return ResponseEntity.ok("Customer registered successfully!");
    }

    @PostMapping(value = "/signin", produces = "application/json", consumes="application/json")
    public ResponseEntity<?> singIn(@RequestBody CustomerDTO customerDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(customerDTO.getEmail(), customerDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        Customer customer = (Customer) authentication.getPrincipal();

        List<String> roles = customer.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                customer.getUsername(),
                customer.getName(),
                roles));
    }
}

