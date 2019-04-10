package dlugolecki.pawel.repository.interfaces;

import dlugolecki.pawel.generic.GenericRepository;
import dlugolecki.pawel.model.entities.Country;

import java.util.Optional;

public interface CountryRepository extends GenericRepository<Country> {

    Optional<Country> findOneByName(String name);
}
