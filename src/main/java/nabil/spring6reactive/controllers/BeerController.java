package nabil.spring6reactive.controllers;

import lombok.RequiredArgsConstructor;
import nabil.spring6reactive.model.BeerDTO;
import nabil.spring6reactive.services.BeerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author Ahmed Nabil
 */
@RestController
@RequiredArgsConstructor
public class BeerController {
    public static final String BEER_ENDPOINT = "/api/v2/beers";

    private final BeerService beerService;
    @GetMapping(BEER_ENDPOINT)
    Flux<BeerDTO> findAll() {
        return beerService.findAll();
    }
}
