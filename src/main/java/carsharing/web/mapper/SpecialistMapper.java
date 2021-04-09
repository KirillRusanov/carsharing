package carsharing.web.mapper;

import carsharing.dao.model.Specialist;
import carsharing.web.dto.SpecialistDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SpecialistMapper {

    SpecialistDTO convertToDTO(Specialist entity);

    Specialist convertToEntity(SpecialistDTO carDTO);

    List<SpecialistDTO> convertToDTO(List<Specialist> carsEntities);

    List<Specialist> convertToEntity(List<SpecialistDTO> carsDTOs);
}
