package carsharing.dao.repository;

import carsharing.dao.DAO;
import carsharing.dao.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public class ClientRepository extends DAO<Client> {

    public ClientRepository() {
        setEntityClass(Client.class);
    }
}
