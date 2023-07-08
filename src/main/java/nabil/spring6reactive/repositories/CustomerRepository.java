package nabil.spring6reactive.repositories;

import nabil.spring6reactive.domain.Customer;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

/**
 * @author Ahmed Nabil
 */
public interface CustomerRepository extends R2dbcRepository<Customer, Integer> {
}
