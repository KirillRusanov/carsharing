package carsharing.web.controller;

import carsharing.service.RateService;
import carsharing.web.dto.RateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/rates")
@PreAuthorize("hasAnyRole('CUSTOMER', 'SPECIALIST', 'ADMIN')")
public class RateController {

    @Autowired
    private RateService rateService;

    @GetMapping(value = "/list")
    public List<RateDTO> getRateList() {
        return rateService.getAll();
    }

    @GetMapping(value = "/{id}")
    public RateDTO getRateById(@PathVariable("id") Long id) {
        return rateService.findById(id);
    }
}