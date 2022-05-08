package carsharing.web.controller.security;

import carsharing.service.CustomerService;
import carsharing.service.SecurityService;
import carsharing.service.exception.security.JwtAuthenticationException;
import carsharing.web.dto.CustomerAuthenticationDTO;
import carsharing.web.dto.CustomerDTO;
import carsharing.web.dto.CustomerRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SecurityService securityService;

    @PostMapping(value = "/signup")
    public String signUp(@ModelAttribute("customer") @Valid CustomerRegistrationDTO customerDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("customer", customerDTO);
            return "registration";
        }
        securityService.registerUser(customerDTO);
        return "redirect:/api/auth/signin";
    }

    @PostMapping(value = "/signin")
    public String singIn(@ModelAttribute("customerAuthenticationDTO") CustomerAuthenticationDTO customerAuthenticationDTO,
                               HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(customerAuthenticationDTO.getEmail(), customerAuthenticationDTO.getPassword()));
            String jwt = securityService.authorizeUser(authentication, customerAuthenticationDTO);
            Cookie cookie = new Cookie(HttpHeaders.AUTHORIZATION, jwt);
            cookie.setMaxAge(60 * 60 * 24);
            cookie.setDomain("localhost");
            cookie.setPath("/");
            response.addCookie(cookie);
            return "redirect:/m-panel";
        } catch (AuthenticationException ex) {
            throw new JwtAuthenticationException("Wrong password! Try another!");
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
        if(!model.containsAttribute("customer")) {
            model.addAttribute("customer", new CustomerRegistrationDTO());
        }
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

