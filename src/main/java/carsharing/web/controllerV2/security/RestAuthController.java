package carsharing.web.controllerV2.security;

import carsharing.service.CustomerService;
import carsharing.service.SecurityService;
import carsharing.web.dto.CustomerAuthenticationDTO;
import carsharing.web.dto.CustomerDTO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("api/rest/auth")
public class RestAuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SecurityService securityService;

    // TODO решить вопрос с плохими кредами

    @PostMapping(value = "/signup")
    public String signUp(@ModelAttribute("CustomerDTO") CustomerDTO customerDTO) {
        if (customerService.getCustomerDTOByEmail(customerDTO.getEmail()) != null) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already taken.");
        }
        securityService.registerUser(customerDTO);
        return "login";
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
            return "login";
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

