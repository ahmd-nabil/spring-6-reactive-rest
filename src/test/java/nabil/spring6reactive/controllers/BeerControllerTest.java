package nabil.spring6reactive.controllers;

import nabil.spring6reactive.domain.Beer;
import nabil.spring6reactive.model.BeerDTO;
import nabil.spring6reactive.repositories.BeerRepository;
import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    @Test
    public void testFindAll() {
        webTestClient
                .get().uri(BeerController.BEER_ENDPOINT)
                .exchange()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectStatus().isOk()
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }
    @Order(1)
    @Test
    public void testFindById() {
        webTestClient
                .get().uri(BeerController.BEER_ENDPOINT_ID, 1)
                .exchange()
                .expectHeader().valueEquals("Content-Type", "application/json")
                .expectStatus().isOk()
                .expectBody().jsonPath("$.beerName").isEqualTo("Galaxy Cat");
    }
    @Order(1)
    @Test
    public void testFindById_NotFound() {
        webTestClient
                .get().uri(BeerController.BEER_ENDPOINT_ID, Integer.MAX_VALUE)
                .exchange()
                .expectStatus().isNotFound();
    }
    @Order(99)
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


    @Order(100)
    @Test
    public void testSave_BadData() {
        beer.setBeerName("");
        webTestClient
                .post()
                .uri(BeerController.BEER_ENDPOINT)
                .body(Mono.just(beer), BeerDTO.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Order(99)
    @Test
    public void testUpdate() {
        webTestClient
                .put()
                .uri(BeerController.BEER_ENDPOINT_ID, 1)
                .body(Mono.just(beer), BeerDTO.class)
                .exchange()
                .expectStatus().isNoContent();
    }
    @Order(99)
    @Test
    public void testUpdate_NotFound() {
        webTestClient
                .put()
                .uri(BeerController.BEER_ENDPOINT_ID, Integer.MAX_VALUE)
                .body(Mono.just(beer), BeerDTO.class)
                .exchange()
                .expectStatus().isNotFound();
    }
    @Order(99)
    @Test
    public void testUpdate_BadData() {
        beer.setPrice(BigDecimal.valueOf(-1));
        webTestClient
                .put()
                .uri(BeerController.BEER_ENDPOINT_ID, 1)
                .body(Mono.just(beer), BeerDTO.class)
                .exchange()
                .expectStatus().isBadRequest();
    }
    @Order(999)
    @Test
    public void testDelete() {
        webTestClient
                .delete()
                .uri(BeerController.BEER_ENDPOINT_ID, 1)
                .exchange()
                .expectStatus().isNoContent();
    }
}