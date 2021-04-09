package carsharing.web.controller;

import carsharing.service.RateService;
import carsharing.web.dto.RateDTO;
import carsharing.web.mapper.RateMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/rate")
public class RateController {

    @Autowired
    private RateService rateService;

    private RateMapper rateMapper = Mappers.getMapper(RateMapper.class);

    @GetMapping(value = "/list")
    public List<RateDTO> getRateList() {
        return rateMapper.convertToDTO(rateService.getAll());
    }

    @GetMapping(value = "/get/{id}")
    public RateDTO getRateById(@PathVariable("id") Long id) {
        return rateMapper.convertToDTO(rateService.getById(id));
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteRate(@PathVariable("id") Long id) {
        rateService.delete(id);
        return "Rate deleted - " + id;
    }

    @PostMapping(value = "/create", produces = "application/json", consumes="application/json")
    public String createRate(@RequestBody RateDTO rateDTO) {
        rateService.create(rateMapper.convertToEntity(rateDTO));
        return "Rate created";
    }

    @PostMapping(value = "/edit", produces = "application/json", consumes = "application/json")
    public String updateRate(@RequestBody RateDTO rateDTO) {
        rateService.update(rateMapper.convertToEntity(rateDTO));
        return "Rate updated";
    }
}