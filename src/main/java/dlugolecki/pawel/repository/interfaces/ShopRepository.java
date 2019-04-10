package dlugolecki.pawel.repository.interfaces;

import dlugolecki.pawel.generic.GenericRepository;
import dlugolecki.pawel.model.entities.Shop;

import java.util.Optional;

public interface ShopRepository extends GenericRepository<Shop> {

    Optional<Shop> findOneByName(String name);

    Optional<Shop> findGivenShop(String shopName, String countryName);
}
