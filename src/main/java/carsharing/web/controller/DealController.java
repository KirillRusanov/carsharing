package carsharing.web.controller;

import carsharing.service.DealService;
import carsharing.web.dto.DealDTO;
import carsharing.web.mapper.DealMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/deal")
public class DealController {

    @Autowired
    private DealService dealService;

    private DealMapper dealMapper = Mappers.getMapper(DealMapper.class);

    @GetMapping(value = "/list")
    public List<DealDTO> getDealList() {
        return dealMapper.convertToDTO(dealService.getAll());
    }

    @GetMapping(value = "/get/{id}")
    public DealDTO getDealById(@PathVariable("id") Long id) {
        return dealMapper.convertToDTO(dealService.getById(id));
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteDeal(@PathVariable("id") Long id) {
        dealService.delete(id);
        return "Deal deleted - " + id;
    }

    @PostMapping(value = "/create", produces = "application/json", consumes="application/json")
    public String createDeal(@RequestBody DealDTO dealDTO) {
        dealService.create(dealMapper.convertToEntity(dealDTO));
        return "Deal created";
    }

    @PostMapping(value = "/edit", produces = "application/json", consumes = "application/json")
    public String updateDeal(@RequestBody DealDTO dealDTO) {
        dealService.update(dealMapper.convertToEntity(dealDTO));
        return "Deal updated";
    }
}
