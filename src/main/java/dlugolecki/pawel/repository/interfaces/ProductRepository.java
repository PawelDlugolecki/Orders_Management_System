package dlugolecki.pawel.repository.interfaces;

import dlugolecki.pawel.generic.GenericRepository;
import dlugolecki.pawel.model.entities.Product;

import java.util.Optional;

public interface ProductRepository extends GenericRepository<Product> {

    Optional<Product> findOneByName(String productName);

    Optional<Product> findGivenProduct(String productName, String categoryName, String producerName);

}
