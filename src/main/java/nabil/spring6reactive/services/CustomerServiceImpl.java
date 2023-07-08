package nabil.spring6reactive.services;

import lombok.RequiredArgsConstructor;
import nabil.spring6reactive.mappers.CustomerMapper;
import nabil.spring6reactive.model.CustomerDTO;
import nabil.spring6reactive.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Ahmed Nabil
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public Flux<CustomerDTO> findAll() {
        return customerRepository
                .findAll()
                .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> findById(Integer id) {
        return customerRepository
                .findById(id)
                .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> save(CustomerDTO customerDTO) {
        return customerRepository
                .save(customerMapper.customerDTOToCustomer(customerDTO))
                .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> update(Integer id, CustomerDTO customerDTO) {
        return customerRepository
                .findById(id)
                .map(foundCustomer -> {
                    foundCustomer.setFirstName(customerDTO.getFirstName());
                    foundCustomer.setLastName(customerDTO.getLastName());
                    return foundCustomer;
                })
                .flatMap(customerRepository::save)
                .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<CustomerDTO> patch(Integer id, CustomerDTO customerDTO) {
        return customerRepository
                .findById(id)
                .map(foundCustomer -> {
                    if(StringUtils.hasText(customerDTO.getFirstName())) foundCustomer.setFirstName(customerDTO.getFirstName());
                    if(StringUtils.hasText(customerDTO.getLastName())) foundCustomer.setLastName(customerDTO.getLastName());
                    return foundCustomer;
                })
                .flatMap(customerRepository::save)
                .map(customerMapper::customerToCustomerDTO);
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return customerRepository.deleteById(id);
    }
}
