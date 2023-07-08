package nabil.spring6reactive.controllers;

import nabil.spring6reactive.domain.Beer;
import nabil.spring6reactive.model.BeerDTO;
import nabil.spring6reactive.repositories.BeerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

/**
 * @author Ahmed Nabil
 */
@SpringBootTest
@AutoConfigureWebTestClient
class BeerControllerTest {
    @Autowired
    WebTestClient webTestClient;

    @Autowired
    BeerRepository beerRepository;
    Beer beer;
    @BeforeEach
    void setup() {
        beer = Beer.builder()
                .beerName("name")
                .beerStyle("IPA")
                .price(BigDecimal.TEN)
                .quantityOnHand(100)
                .upc("123123")
                .build();
    }
    @Test
    public void testFindAll() {
        webTestClient
                .get().uri(BeerController.BEER_ENDPOINT)
                .exchange()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectStatus().isOk()
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }

    @Test
    public void testFindById() {
        webTestClient
                .get().uri(BeerController.BEER_ENDPOINT_ID, 1)
                .exchange()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectStatus().isOk()
                .expectBody().jsonPath("$.beerName").isEqualTo("Galaxy Cat");
    }

    @Test
    public void testSave() {
        webTestClient
                .post()
                .uri(BeerController.BEER_ENDPOINT)
                .body(Mono.just(beer), BeerDTO.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().exists("Location");
    }

    @Test
    public void testUpdate() {
        webTestClient
                .put()
                .uri(BeerController.BEER_ENDPOINT_ID, 1)
                .body(Mono.just(beer), BeerDTO.class)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void testDelete() {
        webTestClient
                .delete()
                .uri(BeerController.BEER_ENDPOINT_ID, 1)
                .exchange()
                .expectStatus().isNoContent();
    }
}