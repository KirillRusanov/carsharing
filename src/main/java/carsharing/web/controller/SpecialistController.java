package carsharing.web.controller;

import carsharing.service.SpecialistService;
import carsharing.web.dto.DealDTO;
import carsharing.web.dto.SpecialistDTO;
import carsharing.web.mapper.DealMapper;
import carsharing.web.mapper.SpecialistMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/specialist")
public class SpecialistController {

    @Autowired
    private SpecialistService specialistService;

    private SpecialistMapper specialistMapper = Mappers.getMapper(SpecialistMapper.class);

    private DealMapper dealMapper = Mappers.getMapper(DealMapper.class);


    @GetMapping(value = "/list")
    public List<SpecialistDTO> getSpecialistList() {
        return specialistMapper.convertToDTO(specialistService.getAll());
    }

    @GetMapping(value = "/get/{id}")
    public SpecialistDTO getSpecialistById(@PathVariable("id") Long id) {
        return specialistMapper.convertToDTO(specialistService.getById(id));
    }

    @GetMapping(value = "/get/deals/active")
    public List<DealDTO> getActiveDeals() {
        return dealMapper.convertToDTO(specialistService.getActiveDeals());
    }
}
