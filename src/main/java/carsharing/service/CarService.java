package carsharing.service;

import carsharing.dao.model.Car;
import carsharing.dao.repository.CarRepository;
import carsharing.web.dto.CarDTO;
import carsharing.web.mapper.CarMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    private CarMapper carMapper = Mappers.getMapper(CarMapper.class);

    public ArrayList<CarDTO> getAll() {
        return (ArrayList<CarDTO>) carMapper.convertToDTO(carRepository.findAll());
    }

    public Car findById(Long id) {
        return carRepository.findById(id);
    }
    public CarDTO findByIdForView(Long id) {
        return carMapper.convertToDTO(carRepository.findById(id));
    }

    public void delete(Long id) {
        carRepository.delete(id);
    }

    public void save(CarDTO carDTO) {
        carRepository.saveOrUpdate(carMapper.convertToEntity(carDTO));
    }

    public void save(Car car) {
        carRepository.saveOrUpdate(car);
    }

    public List<CarDTO> getAvailableCars() {
        return carMapper.convertToDTO(carRepository.getAvailableCars());
    }

}
