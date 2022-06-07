package carsharing.web.mapper;

import carsharing.dao.model.Customer;
import carsharing.web.dto.CustomerDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {

    CustomerDTO convertToDTO(Customer entity);

    Customer convertToEntity(CustomerDTO customerDTO);

    List<CustomerDTO> convertToDTO(List<Customer> customerEntities);

    List<Customer> convertToEntity(List<CustomerDTO> customerDTOS);
}
