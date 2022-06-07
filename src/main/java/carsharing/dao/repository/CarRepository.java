package carsharing.dao.repository;

import carsharing.dao.model.Car;
import carsharing.dao.model.CarStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findAllByCarStatus(CarStatus status);
}
