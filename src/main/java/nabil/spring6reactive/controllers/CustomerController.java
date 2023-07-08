package nabil.spring6reactive.controllers;

import lombok.RequiredArgsConstructor;
import nabil.spring6reactive.model.CustomerDTO;
import nabil.spring6reactive.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author Ahmed Nabil
 */

@RequiredArgsConstructor
@RestController
public class CustomerController {

    private final CustomerService customerService;
    public static final String CUSTOMER_ENDPOINT = "/api/v2/customers";
    public static final String CUSTOMER_ENDPOINT_ID = CUSTOMER_ENDPOINT + "/{customerId}";

    @GetMapping(CUSTOMER_ENDPOINT)
    Flux<CustomerDTO> findAll() {
        return customerService.findAll();
    }

    @GetMapping(CUSTOMER_ENDPOINT_ID)
    Mono<CustomerDTO> findById(@PathVariable(name = "customerId") Integer id) {
        return customerService.findById(id);
    }

    @PostMapping(CUSTOMER_ENDPOINT)
    Mono<ResponseEntity<Void>> save(@Validated @RequestBody CustomerDTO customerDTO) {
        return customerService
                .save(customerDTO)
                .map(savedDto ->
                        ResponseEntity
                                .created(URI.create("http://localhost:8080"+CUSTOMER_ENDPOINT+ "/" +savedDto.getId()))
                                .build()
                );
    }

    @PutMapping(CUSTOMER_ENDPOINT_ID)
    Mono<ResponseEntity<Void>> update(@PathVariable(name = "customerId") Integer id,
                                      @Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.update(id, customerDTO).then(Mono.just(ResponseEntity.noContent().build()));
    }

    @PatchMapping(CUSTOMER_ENDPOINT_ID)
    Mono<ResponseEntity<Void>> patch(@PathVariable("customerId") Integer id,
                                     @Validated @RequestBody CustomerDTO customerDTO) {
        return customerService.patch(id, customerDTO).then(Mono.just(ResponseEntity.noContent().build()));
    }

    @DeleteMapping(CUSTOMER_ENDPOINT_ID)
    Mono<ResponseEntity<Void>> deleteById(@PathVariable("customerId") Integer id) {
        return customerService
                .deleteById(id)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
