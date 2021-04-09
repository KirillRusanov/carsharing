package carsharing.web.mapper;

import carsharing.dao.model.Client;
import carsharing.web.dto.ClientDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {

    ClientDTO convertToDTO(Client entity);

    Client convertToEntity(ClientDTO carDTO);

    List<ClientDTO> convertToDTO(List<Client> carsEntities);

    List<Client> convertToEntity(List<ClientDTO> carsDTOs);
}
