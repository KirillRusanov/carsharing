package carsharing.web.controller;

import carsharing.service.DealService;
import carsharing.service.RateService;
import carsharing.service.SpecialistService;
import carsharing.web.dto.RateDTO;
import carsharing.web.dto.SpecialistDTO;
import carsharing.web.mapper.RateMapper;
import carsharing.web.mapper.SpecialistMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/admin-management")
public class AdminController {

    @Autowired
    private SpecialistService specialistService;
    @Autowired
    private DealService dealService;
    @Autowired
    private RateService rateService;

    private SpecialistMapper specialistMapper = Mappers.getMapper(SpecialistMapper.class);
    private RateMapper rateMapper = Mappers.getMapper(RateMapper.class);

    @GetMapping(value = "/specialists")
    public List<SpecialistDTO> getSpecialistList() {
        return specialistMapper.convertToDTO(specialistService.getAll());
    }

    @GetMapping(value = "/rate/{id}/delete")
    public String deleteRate(@PathVariable("id") Long id) {
        rateService.delete(id);
        return "Rate deleted - " + id;
    }

    @PostMapping(value = "/rate/create", produces = "application/json", consumes="application/json")
    public String createRate(@RequestBody RateDTO rateDTO) {
        rateService.create(rateMapper.convertToEntity(rateDTO));
        return "Rate created";
    }

    @PostMapping(value = "/rate/{id}/edit", produces = "application/json", consumes = "application/json")
    public String updateRate(@PathVariable("id") Long id, @RequestBody RateDTO rateDTO) {
        rateService.update(rateMapper.convertToEntity(rateDTO));
        return "Rate updated";
    }

    @GetMapping(value = "deal/{id}/delete")
    public String deleteDeal(@PathVariable("id") Long id) {
        dealService.delete(id);
        return "Deal deleted - " + id;
    }

}
