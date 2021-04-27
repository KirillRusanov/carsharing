package carsharing.web.mapper;

import carsharing.dao.model.Car;
import carsharing.web.dto.CarDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CarMapper {

    CarDTO convertToDTO(Car entity);

    Car convertToEntity(CarDTO carDTO);

    List<CarDTO> convertToDTO(List<Car> carsEntities);

    List<Car> convertToEntity(List<CarDTO> carsDTOs);
}
