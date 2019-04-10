package dlugolecki.pawel.repository.implement;

import dlugolecki.pawel.generic.AbstractGenericRepository;
import dlugolecki.pawel.model.entities.Country;
import dlugolecki.pawel.repository.interfaces.CountryRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class CountryRepositoryImpl extends AbstractGenericRepository<Country>
        implements CountryRepository {

    @Override
    public Optional<Country> findOneByName(String name) {

        Optional<Country> countryOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select c from Country c where c.name = :name");
            query.setParameter("name", name);
            countryOp = Optional.of((Country) query.getSingleResult());
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return countryOp;
    }
}
