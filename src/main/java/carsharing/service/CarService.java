package carsharing.service;

import carsharing.dao.model.Car;
import carsharing.dao.model.CarStatus;
import carsharing.dao.repository.CarRepository;
import carsharing.service.exception.ServerNotFoundException;
import carsharing.web.dto.CarDTO;
import carsharing.web.mapper.CarMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    private CarMapper carMapper = Mappers.getMapper(CarMapper.class);

    public ArrayList<CarDTO> getAll() {
        return (ArrayList<CarDTO>) carMapper.convertToDTO(Streamable.of(carRepository.findAll()).toList());
    }

    protected Car getById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new ServerNotFoundException("Car with this ID not found!"));
    }

    public CarDTO findById(Long id) {
        return carMapper.convertToDTO(getById(id));
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }

    public void save(CarDTO carDTO) {
        carRepository.save(carMapper.convertToEntity(carDTO));
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public List<CarDTO> getAvailableCars() {
        return carMapper.convertToDTO(carRepository.findAllByCarStatus(CarStatus.AVAILABLE));
    }

}
