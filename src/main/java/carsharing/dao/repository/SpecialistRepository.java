package carsharing.dao.repository;

import carsharing.dao.model.Specialist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialistRepository extends CrudRepository<Specialist, Long> {

}
