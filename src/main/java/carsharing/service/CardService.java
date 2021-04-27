package carsharing.service;

import carsharing.dao.model.Card;
import carsharing.dao.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card findById(Long id) {
        return cardRepository.findById(id);
    }

    public void delete(Long id) {
        cardRepository.delete(id);
    }

    public void save(Card entity) {
        cardRepository.saveOrUpdate(entity);
    }

}
