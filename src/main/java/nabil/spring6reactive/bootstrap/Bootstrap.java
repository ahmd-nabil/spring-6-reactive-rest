package nabil.spring6reactive.bootstrap;

import lombok.RequiredArgsConstructor;
import nabil.spring6reactive.domain.Beer;
import nabil.spring6reactive.domain.Customer;
import nabil.spring6reactive.repositories.BeerRepository;
import nabil.spring6reactive.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Ahmed Nabil
 */
@Component
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {
    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;
    @Override
    public void run(String... args) {
        beerRepository.count().subscribe(count -> {
            if(count == 0) {
                populateBeers();
            }
        });

        customerRepository.count().subscribe(count -> {
            if(count == 0) {
                populateCustomers();
            }
        });

        customerRepository.count().subscribe(System.out::println);
    }


    private void populateBeers() {
        Beer beer1 = Beer.builder()
                .beerName("Galaxy Cat")
                .beerStyle("Pale Ale")
                .upc("12356")
                .price(new BigDecimal("12.99"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .beerName("Crank")
                .beerStyle("Pale Ale")
                .upc("12356222")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .beerName("Sunshine City")
                .beerStyle("IPA")
                .upc("12356")
                .price(new BigDecimal("13.99"))
                .quantityOnHand(144)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        beerRepository.save(beer1).subscribe();
        beerRepository.save(beer2).subscribe();
        beerRepository.save(beer3).subscribe();
    }

    private void populateCustomers() {
        Customer customer1 = Customer.builder().firstName("Ahmed").lastName("Nabil").build();
        Customer customer2 = Customer.builder().firstName("Mike").lastName("Ross").build();
        Customer customer3 = Customer.builder().firstName("Phoebe").lastName("Buffay").build();
        customerRepository.save(customer1).subscribe();
        customerRepository.save(customer2).subscribe();
        customerRepository.save(customer3).subscribe();
    }
}
