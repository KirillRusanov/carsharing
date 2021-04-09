package carsharing.service;

import carsharing.dao.model.Card;
import carsharing.dao.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public ArrayList<Card> getAll() {
        return (ArrayList<Card>) cardRepository.findAll();
    }

    public Card getById(Long id) {
        return cardRepository.findById(id);
    }

    public void delete(Long id) {
        cardRepository.deleteById(id);
    }

    public void create(Card entity) {
        cardRepository.create(entity);
    }

    public void update(Card entity) {
        cardRepository.update(entity);
    }
}
