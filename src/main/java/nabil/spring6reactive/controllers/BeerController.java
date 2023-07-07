package nabil.spring6reactive.controllers;

import lombok.RequiredArgsConstructor;
import nabil.spring6reactive.model.BeerDTO;
import nabil.spring6reactive.services.BeerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Ahmed Nabil
 */
@RestController
@RequiredArgsConstructor
public class BeerController {
    public static final String BEER_ENDPOINT = "/api/v2/beers";
    public static final String BEER_ENDPOINT_ID = BEER_ENDPOINT + "/{beerId}";

    private final BeerService beerService;

    @GetMapping(BEER_ENDPOINT)
    Flux<BeerDTO> findAll() {
        return beerService.findAll();
    }
    @GetMapping(BEER_ENDPOINT_ID)
    Mono<BeerDTO> findById(@PathVariable(name = "beerId") Integer id) {
        return beerService.findById(id);
    }
}
