package carsharing.dao.repository;

import carsharing.dao.model.Customer;
import carsharing.dao.model.Deal;
import carsharing.dao.model.DealStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealRepository extends CrudRepository<Deal, Long> {

    List<Deal> findAllByStatus(DealStatus dealStatus);

    List<Deal> findAllByCustomer(Customer customer);
}
