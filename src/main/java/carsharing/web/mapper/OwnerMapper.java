package carsharing.web.mapper;

import carsharing.dao.model.Owner;
import carsharing.web.dto.OwnerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OwnerMapper {

    OwnerDTO convertToDTO(Owner entity);

    Owner convertToEntity(OwnerDTO carDTO);

    List<OwnerDTO> convertToDTO(List<Owner> carsEntities);

    List<Owner> convertToEntity(List<OwnerDTO> carsDTOs);
}
