package nabil.spring6reactive.controllers;

import nabil.spring6reactive.domain.Customer;
import nabil.spring6reactive.model.CustomerDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

/**
 * @author Ahmed Nabil
 */
@SpringBootTest
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {

    @Autowired
    WebTestClient webTestClient;
    Customer customer;
    @BeforeEach
    void setUp() {
        customer = Customer.builder().firstName("Ahmed").lastName("Nabil").build();
    }

    @Test
    @Order(1)
    void testFindAll() {
        webTestClient
                .get()
                .uri(CustomerController.CUSTOMER_ENDPOINT)
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("$.size()").isEqualTo(3);
    }

    @Test
    @Order(1)
    void testFindById() {
        webTestClient
                .get()
                .uri(CustomerController.CUSTOMER_ENDPOINT_ID, 1)
                .exchange()
                .expectStatus().isOk()
                .expectBody(CustomerDTO.class);
    }

    @Test
    @Order(99)
    void testSave() {
        webTestClient
                .post()
                .uri(CustomerController.CUSTOMER_ENDPOINT)
                .body(Mono.just(customer), CustomerDTO.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().exists("Location");
    }

    @Test
    @Order(99)
    void testUpdate() {
        webTestClient
                .put()
                .uri(CustomerController.CUSTOMER_ENDPOINT_ID, 2)
                .body(Mono.just(customer), CustomerDTO.class)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @Order(99)
    void testPatch() {
        webTestClient
                .patch()
                .uri(CustomerController.CUSTOMER_ENDPOINT_ID, 1)
                .body(Mono.just(Customer.builder().firstName("AAA").build()), CustomerDTO.class)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    @Order(999)
    void testDeleteById() {
        webTestClient
                .delete()
                .uri(CustomerController.CUSTOMER_ENDPOINT_ID, 1)
                .exchange()
                .expectStatus().isNoContent();
    }
}