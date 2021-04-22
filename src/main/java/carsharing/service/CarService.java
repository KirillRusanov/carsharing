package carsharing.service;

import carsharing.dao.model.Car;
import carsharing.dao.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public ArrayList<Car> getAll() {
        return (ArrayList<Car>) carRepository.findAll();
    }

    public Car findById(Long id) {
        return carRepository.findById(id);
    }

    public void delete(Long id) {
        carRepository.delete(id);
    }

    public void save(Car entity) {
        carRepository.saveOrUpdate(entity);
    }

    public List<Car> getAvailableCars() {
        return carRepository.getAvailableCars();
    }

}
