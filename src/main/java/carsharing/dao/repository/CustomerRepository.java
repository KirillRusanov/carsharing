package carsharing.dao.repository;

import carsharing.dao.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByEmail(String email);

    Customer findByPhoneNumber(String phoneNumber);
}
