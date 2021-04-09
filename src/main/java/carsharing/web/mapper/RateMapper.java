package carsharing.web.mapper;

import carsharing.dao.model.Rate;
import carsharing.web.dto.RateDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RateMapper {

    RateDTO convertToDTO(Rate entity);

    Rate convertToEntity(RateDTO carDTO);

    List<RateDTO> convertToDTO(List<Rate> carsEntities);

    List<Rate> convertToEntity(List<RateDTO> carsDTOs);

}
