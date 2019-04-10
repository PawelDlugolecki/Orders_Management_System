package dlugolecki.pawel.repository.interfaces;

import dlugolecki.pawel.generic.GenericRepository;
import dlugolecki.pawel.model.entities.Customer;

import java.util.Optional;

public interface CustomerRepository extends GenericRepository<Customer> {

    Optional<Customer> findGivenCustomer(String name, String surname, String countryName);

}
