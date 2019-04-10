package dlugolecki.pawel.repository.interfaces;

import dlugolecki.pawel.generic.GenericRepository;
import dlugolecki.pawel.model.entities.Producer;

import java.util.Optional;

public interface ProducerRepository extends GenericRepository<Producer> {

    Optional<Producer> findGivenProducer(String name, String countryName, String tradeName);
}
