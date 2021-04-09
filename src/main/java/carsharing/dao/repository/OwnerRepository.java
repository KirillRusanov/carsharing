package carsharing.dao.repository;

import carsharing.dao.DAO;
import carsharing.dao.model.Owner;
import org.springframework.stereotype.Repository;

@Repository
public class OwnerRepository extends DAO<Owner> {

    public OwnerRepository() {
        setEntityClass(Owner.class);
    }
}
