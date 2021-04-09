package carsharing.dao.repository;

import carsharing.dao.DAO;
import carsharing.dao.model.Card;
import org.springframework.stereotype.Repository;

@Repository
public class CardRepository extends DAO<Card> {

    public CardRepository() {
        setEntityClass(Card.class);
    }
}
