package carsharing.dao.repository;

import carsharing.dao.DAO;
import carsharing.dao.model.Car;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepository extends DAO<Car> {

    public CarRepository() {
        setEntityClass(Car.class);
    }
}
