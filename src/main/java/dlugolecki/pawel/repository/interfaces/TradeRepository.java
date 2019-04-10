package dlugolecki.pawel.repository.interfaces;

import dlugolecki.pawel.generic.GenericRepository;
import dlugolecki.pawel.model.entities.Trade;

import java.util.Optional;

public interface TradeRepository extends GenericRepository<Trade> {

    Optional<Trade> findOneByName(String name);

    Optional<Trade> findGivenTrade(String name);
}
