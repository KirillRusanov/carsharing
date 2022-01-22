package carsharing.service;

import carsharing.dao.model.Card;
import carsharing.dao.repository.CardRepository;
import carsharing.web.dto.CardDTO;
import carsharing.web.mapper.CardMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    private CardMapper cardMapper = Mappers.getMapper(CardMapper.class);

    public CardDTO findById(Long id) {
        return cardMapper.convertToDTO(cardRepository.findById(id));
    }

    public void delete(Long id) {
        cardRepository.delete(id);
    }

    public void save(CardDTO cardDTO) {
        cardRepository.saveOrUpdate(cardMapper.convertToEntity(cardDTO));
    }

    protected void save(Card card) {
        cardRepository.saveOrUpdate(card);
    }

}
