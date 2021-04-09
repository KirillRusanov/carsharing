package carsharing.dao.repository;

import carsharing.dao.DAO;
import carsharing.dao.model.Rate;
import org.springframework.stereotype.Repository;

@Repository
public class RateRepository extends DAO<Rate> {

    public RateRepository() {
        setEntityClass(Rate.class);
    }
}
