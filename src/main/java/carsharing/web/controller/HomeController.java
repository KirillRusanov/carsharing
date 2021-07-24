package carsharing.web.controller;

import carsharing.web.dto.CustomerAuthenticationDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = "/index")
    public String getHomePage() {
        return "index";
    }

    @GetMapping(value = "/m-panel")
    public String getPanel(Authentication authentication, Model model) {
        if (authentication == null) {
            model.addAttribute("customer", new CustomerAuthenticationDTO());
            return "login";
        }
        return "panel";
    }
}
