package nabil.spring6reactive.services;

import nabil.spring6reactive.model.BeerDTO;
import reactor.core.publisher.Flux;

/**
 * @author Ahmed Nabil
 */
public interface BeerService {
    Flux<BeerDTO>  findAll();
}
