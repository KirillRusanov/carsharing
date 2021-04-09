package carsharing.web.controller;

import carsharing.service.SpecialistService;
import carsharing.web.dto.SpecialistDTO;
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

    @GetMapping(value = "/list")
    public List<SpecialistDTO> getSpecialistList() {
        return specialistMapper.convertToDTO(specialistService.getAll());
    }

    @GetMapping(value = "/get/{id}")
    public SpecialistDTO getSpecialistById(@PathVariable("id") Long id) {
        return specialistMapper.convertToDTO(specialistService.getById(id));
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteSpecialist(@PathVariable("id") Long id) {
        specialistService.delete(id);
        return "Specialist deleted - " + id;
    }

    @PostMapping(value = "/create", produces = "application/json", consumes="application/json")
    public String createSpecialist(@RequestBody SpecialistDTO specialistDTO) {
        specialistService.create(specialistMapper.convertToEntity(specialistDTO));
        return "Specialist created";
    }

    @PostMapping(value = "/edit", produces = "application/json", consumes = "application/json")
    public String updateSpecialist(@RequestBody SpecialistDTO specialistDTO) {
        specialistService.update(specialistMapper.convertToEntity(specialistDTO));
        return "Specialist updated";
    }
}
