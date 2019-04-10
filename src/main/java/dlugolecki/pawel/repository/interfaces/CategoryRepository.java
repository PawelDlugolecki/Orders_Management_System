package dlugolecki.pawel.repository.interfaces;

import dlugolecki.pawel.generic.GenericRepository;
import dlugolecki.pawel.model.entities.Category;

import java.util.Optional;

public interface CategoryRepository extends GenericRepository<Category> {

    Optional<Category> findOneByName(String name);

}
