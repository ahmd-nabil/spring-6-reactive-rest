package nabil.spring6reactive.controllers;

import lombok.RequiredArgsConstructor;
import nabil.spring6reactive.model.BeerDTO;
import nabil.spring6reactive.services.BeerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

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
    @PostMapping(path = BEER_ENDPOINT, consumes = MediaType.APPLICATION_JSON_VALUE)
    Mono<ResponseEntity<Void>> saveBeer(@RequestBody BeerDTO beerDTO) {
        return beerService.save(beerDTO)
                .map(saved ->
                        ResponseEntity
                                .created(
                                        URI.create("http://localhost:8080" + BEER_ENDPOINT + "/" + saved.getId()
                                        )
                                ).build()
                );
    }
}
