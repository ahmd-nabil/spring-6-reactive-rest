package nabil.spring6reactive.repositories;

import nabil.spring6reactive.config.DatabaseConfig;
import nabil.spring6reactive.domain.Beer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

/**
 * @author Ahmed Nabil
 */
@DataR2dbcTest
@Import(DatabaseConfig.class)
class BeerRepositoryTest {
    @Autowired
    BeerRepository beerRepository;

    private Beer beer;
    @BeforeEach
    public void setup() {
        beer = Beer.builder()
                .beerName("name")
                .beerStyle("IPA")
                .price(BigDecimal.TEN)
                .quantityOnHand(100)
                .upc("123123")
                .build();
    }

    @Test
    public void saveBeerTest() {
        Mono<Beer> beerMono = beerRepository.save(beer);
        StepVerifier
                .create(beerMono)
                .expectNext(beer)
                .verifyComplete();
    }
}