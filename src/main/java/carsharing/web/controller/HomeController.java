package carsharing.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping(value = "/index")
    public ModelAndView getHomePage(ModelAndView modelAndView) {
        modelAndView.setViewName("index.html");
        return modelAndView;
    }
}
