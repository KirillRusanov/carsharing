package carsharing.web.mapper;

import carsharing.dao.model.Deal;
import carsharing.web.dto.DealDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DealMapper {
    DealDTO convertToDTO(Deal entity);

    Deal convertToEntity(DealDTO carDTO);

    List<DealDTO> convertToDTO(List<Deal> carsEntities);

    List<Deal> convertToEntity(List<DealDTO> carsDTOs);
}
