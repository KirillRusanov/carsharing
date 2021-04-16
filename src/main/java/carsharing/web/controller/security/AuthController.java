package carsharing.web.controller.security;

import carsharing.configuration.utils.CookiesConfigurer;
import carsharing.configuration.utils.JwtUtils;
import carsharing.service.CustomerService;
import carsharing.web.dto.CustomerAuthenticationDTO;
import carsharing.web.mapper.CustomerMapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;


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
    private CustomerService customerService;

    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @PostMapping(value = "/signup", produces = "application/json", consumes="application/json")
    public ResponseEntity<?> signUp(@RequestBody CustomerAuthenticationDTO customerAuthenticationDTO) {
        if (customerService.getCustomerByEmail(customerAuthenticationDTO.getEmail()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already taken!");
        }
        customerService.save(customerMapper.convertToEntity(customerAuthenticationDTO));
        return ResponseEntity.ok("Customer registered successfully!");
    }

    @PostMapping(value = "/signin", produces = "application/json", consumes="application/json")
    public String singIn(@RequestBody CustomerAuthenticationDTO customerAuthenticationDTO,
                         HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(customerAuthenticationDTO.getEmail(), customerAuthenticationDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        response.addCookie(cookiesConfigurer.configureCookie(jwt));
        return "redirect: /carsharing/api/customer/list";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Arrays.stream(request
                .getCookies())
                .peek(a -> a.setMaxAge(0))
                .forEach(response::addCookie);
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        return "redirect: /carsharing/api/customer/list";
    }
}

