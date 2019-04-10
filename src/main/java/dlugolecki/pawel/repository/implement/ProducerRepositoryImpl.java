package dlugolecki.pawel.repository.implement;

import dlugolecki.pawel.exception.MyException;
import dlugolecki.pawel.generic.AbstractGenericRepository;
import dlugolecki.pawel.model.entities.Producer;
import dlugolecki.pawel.repository.interfaces.ProducerRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class ProducerRepositoryImpl extends AbstractGenericRepository<Producer>
        implements ProducerRepository {

    @Override
    public Optional<Producer> findGivenProducer(String name, String countryName, String tradeName) {

        Optional<Producer> producerOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            producerOp = entityManager.createQuery("select c from Producer c where c.name = :name and c.country.name = :country and c.trade.name = :trade", Producer.class)
                    .setParameter("name", name)
                    .setParameter("country", countryName)
                    .setParameter("trade", tradeName)
                    .getResultList().stream().findFirst();
            tx.commit();
        } catch (MyException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return producerOp;
    }
}
