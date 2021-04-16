package carsharing.web.controller;

import carsharing.service.CardService;
import carsharing.web.dto.CardDTO;
import carsharing.web.mapper.CardMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/card")
public class CardController {

    @Autowired
    private CardService cardService;

    private CardMapper cardMapper = Mappers.getMapper(CardMapper.class);

    @GetMapping(value = "/list")
    public List<CardDTO> getOwnCardList() {
        return cardMapper.convertToDTO(cardService.getAll());
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteCard(@PathVariable("id") Long id) {
        cardService.delete(id);
        return "Card delete - " + id;
    }

    @PostMapping(value = "/add", produces = "application/json", consumes="application/json")
    public String addCard(@RequestBody CardDTO cardDTO) {
        cardService.create(cardMapper.convertToEntity(cardDTO));
        return "Card added";
    }
}
