package nabil.spring6reactive.services;

import nabil.spring6reactive.model.BeerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Ahmed Nabil
 */
public interface BeerService {
    Flux<BeerDTO>  findAll();
    Mono<BeerDTO> findById(Integer id);
    Mono<BeerDTO> save(BeerDTO beerDTO);
}
