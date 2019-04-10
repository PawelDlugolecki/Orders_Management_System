package dlugolecki.pawel.repository.implement;

import dlugolecki.pawel.exception.MyException;
import dlugolecki.pawel.generic.AbstractGenericRepository;
import dlugolecki.pawel.model.entities.Shop;
import dlugolecki.pawel.repository.interfaces.ShopRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class ShopRepositoryImpl extends AbstractGenericRepository<Shop>
        implements ShopRepository {

    @Override
    public Optional<Shop> findOneByName(String name) {

        Optional<Shop> shopOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select c from Shop c where c.name = :name");
            query.setParameter("name", name);
            shopOp = Optional.of((Shop) query.getSingleResult());
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
        return shopOp;
    }

    @Override
    public Optional<Shop> findGivenShop(String shopName, String countryName) {

        Optional<Shop> op = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            op = entityManager.createQuery("select c from Shop c where c.name = :name and c.country.name = :country", Shop.class)
                    .setParameter("name", shopName)
                    .setParameter("country", countryName)
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
        return op;
    }
}
