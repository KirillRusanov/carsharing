package carsharing.dao.repository;

import carsharing.dao.DAO;
import carsharing.dao.model.Specialist;
import org.springframework.stereotype.Repository;

@Repository
public class SpecialistRepository extends DAO<Specialist> {

    public SpecialistRepository() {
        setEntityClass(Specialist.class);
    }
}
