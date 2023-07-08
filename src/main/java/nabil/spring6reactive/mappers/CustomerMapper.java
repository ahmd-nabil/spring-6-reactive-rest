package nabil.spring6reactive.mappers;

import nabil.spring6reactive.domain.Customer;
import nabil.spring6reactive.model.CustomerDTO;
import org.mapstruct.Mapper;

/**
 * @author Ahmed Nabil
 */
@Mapper
public interface CustomerMapper {
    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer CustomerDTOToCustomer(CustomerDTO customerDTO);
}
