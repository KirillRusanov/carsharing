package carsharing.service;

import carsharing.dao.model.Card;
import carsharing.dao.repository.CardRepository;
import carsharing.service.exception.ServerNotFoundException;
import carsharing.web.dto.CardDTO;
import carsharing.web.mapper.CardMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    private CardMapper cardMapper = Mappers.getMapper(CardMapper.class);

    protected Card findById(Long id) {
        return cardRepository.findById(id).orElseThrow(() -> new ServerNotFoundException("Card with this ID not found!"));
    }

    public CardDTO getById(Long id) {
        return cardMapper.convertToDTO(findById(id));
    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }

    public void save(CardDTO cardDTO) {
        cardRepository.save(cardMapper.convertToEntity(cardDTO));
    }

    protected void save(Card card) {
        cardRepository.save(card);
    }

}
