package carsharing.dao.repository;

import carsharing.dao.model.Car;
import carsharing.dao.model.CarStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findAllByCarStatus(CarStatus status);
}
