package nabil.spring6reactive.services;

import lombok.RequiredArgsConstructor;
import nabil.spring6reactive.mappers.BeerMapper;
import nabil.spring6reactive.model.BeerDTO;
import nabil.spring6reactive.repositories.BeerRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> findById(Integer id) {
        return beerRepository.findById(id).map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> save(BeerDTO beerDTO) {
        return beerRepository
                .save(
                        beerMapper.beerDTOToBeer(beerDTO)
                )
                .map(beerMapper::beerToBeerDTO);
    }
    @Override
    public Mono<BeerDTO> update(Integer id, BeerDTO beerDTO) {
        return beerRepository.findById(id)
                .map(foundBeer -> {
                    foundBeer.setBeerName(beerDTO.getBeerName());
                    foundBeer.setBeerStyle(beerDTO.getBeerStyle());
                    foundBeer.setPrice(beerDTO.getPrice());
                    foundBeer.setUpc(beerDTO.getUpc());
                    foundBeer.setQuantityOnHand(beerDTO.getQuantityOnHand());
                    return foundBeer;
                })
                .flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<BeerDTO> patch(Integer id, BeerDTO beerDTO) {
        return beerRepository.findById(id)
                .map(foundBeer -> {
                    if(StringUtils.hasText(beerDTO.getBeerName())) {
                        foundBeer.setBeerName(beerDTO.getBeerName());
                    }
                    if(StringUtils.hasText(beerDTO.getBeerStyle())) {
                        foundBeer.setBeerStyle(beerDTO.getBeerStyle());
                    }
                    if(beerDTO.getPrice() != null) {
                        foundBeer.setPrice(beerDTO.getPrice());
                    }
                    if(beerDTO.getUpc() != null) {
                        foundBeer.setUpc(beerDTO.getUpc());
                    }
                    if(beerDTO.getQuantityOnHand() != null) {
                        foundBeer.setQuantityOnHand(beerDTO.getQuantityOnHand());
                    }
                    return foundBeer;
                })
                .flatMap(beerRepository::save)
                .map(beerMapper::beerToBeerDTO);
    }

    @Override
    public Mono<Void> deleteById(Integer id) {
        return beerRepository.deleteById(id);
    }
}
