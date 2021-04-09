package carsharing.web.controller;

import carsharing.service.OwnerService;
import carsharing.web.dto.OwnerDTO;
import carsharing.web.mapper.OwnerMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    private OwnerMapper ownerMapper = Mappers.getMapper(OwnerMapper.class);

    @GetMapping(value = "/list")
    public List<OwnerDTO> getOwnerList() {
        return ownerMapper.convertToDTO(ownerService.getAll());
    }

    @GetMapping(value = "/get/{id}")
    public OwnerDTO getOwnerById(@PathVariable("id") Long id) {
        return ownerMapper.convertToDTO(ownerService.getById(id));
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteOwner(@PathVariable("id") Long id) {
        ownerService.delete(id);
        return "Owner deleted - " + id;
    }

    @PostMapping(value = "/create", produces = "application/json", consumes="application/json")
    public String createOwner(@RequestBody OwnerDTO ownerDTO) {
        ownerService.create(ownerMapper.convertToEntity(ownerDTO));
        return "Owner created";
    }

    @PostMapping(value = "/edit", produces = "application/json", consumes = "application/json")
    public String updateOwner(@RequestBody OwnerDTO ownerDTO) {
        ownerService.update(ownerMapper.convertToEntity(ownerDTO));
        return "Owner updated";
    }
}
