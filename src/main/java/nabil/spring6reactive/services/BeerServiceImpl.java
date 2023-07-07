package nabil.spring6reactive.services;

import lombok.RequiredArgsConstructor;
import nabil.spring6reactive.mappers.BeerMapper;
import nabil.spring6reactive.model.BeerDTO;
import nabil.spring6reactive.repositories.BeerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author Ahmed Nabil
 */
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;
    @Override
    public Flux<BeerDTO> findAll() {
        return beerRepository
                .findAll()
                .map(beerMapper::BeerToBeerDTO);
    }
}
