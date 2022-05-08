package carsharing.web.mapper;

import carsharing.dao.model.Deal;
import carsharing.web.dto.DealDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DealMapper {
    DealDTO convertToDTO(Deal entity);

    Deal convertToEntity(DealDTO dealDTO);

    List<DealDTO> convertToDTO(List<Deal> dealEntities);

    List<Deal> convertToEntity(List<DealDTO> dealDTOs);
}
