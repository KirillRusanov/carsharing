package carsharing.web.controller;

import carsharing.web.dto.CustomerAuthenticationDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping(value = "/index")
    public ModelAndView getHomePage(ModelAndView modelAndView) {
        modelAndView.setViewName("index.html");
        return modelAndView;
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
