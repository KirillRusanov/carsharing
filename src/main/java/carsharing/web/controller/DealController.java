package carsharing.web.controller;

import carsharing.service.DealService;
import carsharing.web.dto.DealDTO;
import carsharing.web.mapper.DealMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/deal")
public class DealController {

    @Autowired
    private DealService dealService;

    private DealMapper dealMapper = Mappers.getMapper(DealMapper.class);

    @PostMapping(value = "/rent", produces = "application/json", consumes="application/json")
    public String startDeal(@RequestBody DealDTO dealDTO) {
        dealService.create(dealMapper.convertToEntity(dealDTO));
        return "Deal created";
    }
}
