package dlugolecki.pawel.generic;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T> {

    void add(T t);

    void delete(Long id);

    Optional<T> findOneById(Long id);

    List<T> findAll();

    void deleteAll();
}
