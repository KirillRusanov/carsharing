package carsharing.dao.repository;

import carsharing.dao.DAO;
import carsharing.dao.model.Deal;
import org.springframework.stereotype.Repository;

@Repository
public class DealRepository extends DAO<Deal> {

    public DealRepository() {
        setEntityClass(Deal.class);
    }
}
