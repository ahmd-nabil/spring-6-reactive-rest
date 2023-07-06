package nabil.spring6reactive.repositories;

import nabil.spring6reactive.domain.Beer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author Ahmed Nabil
 */
public interface BeerRepository extends ReactiveCrudRepository<Beer, Integer> {
}
