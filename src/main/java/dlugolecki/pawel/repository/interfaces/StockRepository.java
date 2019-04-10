package dlugolecki.pawel.repository.interfaces;

import dlugolecki.pawel.generic.GenericRepository;
import dlugolecki.pawel.model.entities.Stock;

import java.util.Optional;


public interface StockRepository extends GenericRepository<Stock> {

    Optional<Stock> findGivenStock(Long productId, Long shopId);

    Optional<Stock> findOneStock(String productName, String categoryName, String producerName, String shopName, String countryName);


}
