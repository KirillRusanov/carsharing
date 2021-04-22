package carsharing.web.controller;

import carsharing.dao.model.Customer;
import carsharing.service.CardService;
import carsharing.web.dto.CardDTO;
import carsharing.web.mapper.CardMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    private CardMapper cardMapper = Mappers.getMapper(CardMapper.class);

    @GetMapping(value = "/list")
    public List<CardDTO> getCardList(@AuthenticationPrincipal Customer customerDetails) {
        return cardMapper.convertToDTO(customerDetails.getCards());
    }

    @GetMapping(value = "/{id}/delete")
    public void deleteCard(@PathVariable("id") Long id, @AuthenticationPrincipal Customer customerDetails) {
        if (customerDetails.getCards().contains(cardService.findById(id))) {
            cardService.delete(id);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Denied, not enough rights");
    }

    @PostMapping(value = "/add", produces = "application/json", consumes="application/json")
    public CardDTO addCard(@RequestBody CardDTO cardDTO, @AuthenticationPrincipal Customer customerDetails) {
        cardDTO.setCustomer(customerDetails);
        cardService.save(cardMapper.convertToEntity(cardDTO));
        return cardDTO;
    }
}
