package carsharing.web.controller.security;

import carsharing.dao.model.Customer;
import carsharing.service.CustomerService;
import carsharing.service.RoleService;
import carsharing.service.security.JwtTokenProvider;
import carsharing.web.dto.CustomerAuthenticationDTO;
import carsharing.web.dto.CustomerDTO;
import carsharing.web.mapper.CustomerMapper;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;


@Controller
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private RoleService roleService;
    @Autowired
    public BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private CustomerService customerService;

    private CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @PostMapping(value = "/signup")
    public String signUp(@ModelAttribute("CustomerDTO") CustomerDTO customerDTO) {
        if (customerService.getCustomerByEmail(customerDTO.getEmail()) != null) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already taken.");
        }
        Customer customer = customerMapper.convertToEntity(customerDTO);
        customer.setRoles(Collections.singleton(roleService.findById(1L)));
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
        customerService.save(customer);
        LOG.info("Customer " + customerDTO.getEmail() + " registered");
        return "login";
    }

    @PostMapping(value = "/signin")
    public String singIn(@ModelAttribute("customerAuthenticationDTO") CustomerAuthenticationDTO customerAuthenticationDTO,
                               HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(customerAuthenticationDTO.getEmail(), customerAuthenticationDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Customer customer = customerService.getCustomerByEmail(customerAuthenticationDTO.getEmail());
            String jwt = jwtTokenProvider.createToken(customerAuthenticationDTO.getEmail());
            Cookie cookie = new Cookie(HttpHeaders.AUTHORIZATION, jwt);
            cookie.setMaxAge(60 * 60 * 24);
            cookie.setDomain("localhost");
            cookie.setPath("/");
            response.addCookie(cookie);
            LOG.info("Client \"{}\" logged into the system", customer.getEmail());
            return "redirect:/m-panel";
        } catch (AuthenticationException ex) {
            return "redirect:/api/auth/signin";
        }
    }

    @GetMapping(value = "/signin")
    public String singIn(Authentication authentication, Model model) {
        if (authentication == null) {
            model.addAttribute("customer", new CustomerAuthenticationDTO());
            return "login";
        }
        return "redirect:/m-panel";
    }

    @GetMapping(value = "/signup")
    public String singUp(Model model) {
        model.addAttribute("customer", new CustomerDTO());
        return "registration";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
        return "index";
    }
}

