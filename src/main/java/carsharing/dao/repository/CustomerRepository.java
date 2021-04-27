package carsharing.dao.repository;

import carsharing.dao.DAO;
import carsharing.dao.model.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepository extends DAO<Customer> {

    public CustomerRepository() {
        setEntityClass(Customer.class);
    }
}
