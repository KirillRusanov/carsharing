package carsharing.web.controller;

import carsharing.dao.model.Card;
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
    public List<CardDTO> getCardList() {
        return cardMapper.convertToDTO(cardService.getAll());
    }

    @GetMapping(value = "/get/{id}")
    public CardDTO getCardById(@PathVariable("id") Long id) {
        return cardMapper.convertToDTO(cardService.getById(id));
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteCard(@PathVariable("id") Long id) {
        cardService.delete(id);
        return "Card deleted - " + id;
    }

    @PostMapping(value = "/create", produces = "application/json", consumes="application/json")
    public String createCard(@RequestBody CardDTO cardDTO) {
        cardService.create(cardMapper.convertToEntity(cardDTO));
        return "Card created";
    }

    @PostMapping(value = "/edit", produces = "application/json", consumes = "application/json")
    public String updateCard(@RequestBody CardDTO card) {
        cardService.update(cardMapper.convertToEntity(card));
        return "Card updated";
    }

}
