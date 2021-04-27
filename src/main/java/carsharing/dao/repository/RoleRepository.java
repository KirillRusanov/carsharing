package carsharing.dao.repository;

import carsharing.dao.DAO;
import carsharing.dao.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepository extends DAO<Role> {

    public RoleRepository() {
        setEntityClass(Role.class);
    }
}
