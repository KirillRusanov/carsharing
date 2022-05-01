package carsharing.web.mapper;

import carsharing.dao.model.Specialist;
import carsharing.web.dto.SpecialistDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface SpecialistMapper {

    SpecialistDTO convertToDTO(Specialist entity);

    Specialist convertToEntity(SpecialistDTO specialistDTO);

    List<SpecialistDTO> convertToDTO(List<Specialist> specialistEntities);

    List<Specialist> convertToEntity(List<SpecialistDTO> specialistDTOs);
}
