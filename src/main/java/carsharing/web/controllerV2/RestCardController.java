package carsharing.web.controllerV2;

import carsharing.dao.model.Customer;
import carsharing.service.CardService;
import carsharing.web.dto.CardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/rest/cards")
@PreAuthorize("hasAnyRole('CUSTOMER', 'SPECIALIST', 'ADMIN')")
public class RestCardController {

    @Autowired
    private CardService cardService;

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
        cardService.save(cardDTO);
        return cardDTO;
    }
}
