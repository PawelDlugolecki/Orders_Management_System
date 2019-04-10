package dlugolecki.pawel.repository.implement;

import dlugolecki.pawel.exception.MyException;
import dlugolecki.pawel.generic.AbstractGenericRepository;
import dlugolecki.pawel.model.entities.Trade;
import dlugolecki.pawel.repository.interfaces.TradeRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class TradeRepositoryImpl extends AbstractGenericRepository<Trade>
        implements TradeRepository {

    @Override
    public Optional<Trade> findGivenTrade(String name) {

        Optional<Trade> op = Optional.empty();
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            op = entityManager
                    .createQuery("select c from Trade c where c.name = :name")
                    .setParameter("name", name)
                    .getResultList().stream().findFirst();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return op;
    }

    @Override
    public Optional<Trade> findOneByName(String name) {

        Optional<Trade> tradeOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select t from Trade t where t.name = :name");
            query.setParameter("name", name);
            tradeOp = Optional.of((Trade) query.getSingleResult());
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
        return tradeOp;
    }
}
