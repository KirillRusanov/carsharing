package carsharing.service;

import carsharing.dao.model.Car;
import carsharing.dao.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public ArrayList<Car> getAll() {
        return (ArrayList<Car>) carRepository.findAll();
    }

    public Car getById(Long id) {
        return carRepository.findById(id);
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    public void create(Car entity) {
        carRepository.create(entity);
    }

    public void update(Car entity) {
        carRepository.update(entity);
    }

}
