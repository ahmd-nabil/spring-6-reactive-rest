package nabil.spring6reactive.services;

import nabil.spring6reactive.model.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Ahmed Nabil
 */
public interface CustomerService {
    Flux<CustomerDTO> findAll();
    Mono<CustomerDTO> findById(Integer id);
    Mono<CustomerDTO> save(CustomerDTO customerDTO);
    Mono<CustomerDTO> update(Integer id, CustomerDTO customerDTO);
    Mono<CustomerDTO> patch(Integer id, CustomerDTO customerDTO);
    Mono<Void> deleteById(Integer id);
}
