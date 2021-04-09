package carsharing.web.mapper;

import carsharing.dao.model.Customer;
import carsharing.web.dto.CustomerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerDTO convertToDTO(Customer entity);

    Customer convertToEntity(CustomerDTO carDTO);

    List<CustomerDTO> convertToDTO(List<Customer> carsEntities);

    List<Customer> convertToEntity(List<CustomerDTO> carsDTOs);
}
