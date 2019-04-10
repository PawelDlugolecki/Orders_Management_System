package dlugolecki.pawel.repository.implement;

import dlugolecki.pawel.exception.MyException;
import dlugolecki.pawel.generic.AbstractGenericRepository;
import dlugolecki.pawel.model.entities.Product;
import dlugolecki.pawel.repository.interfaces.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class ProductRepositoryImpl extends AbstractGenericRepository<Product>
        implements ProductRepository {

    @Override
    public Optional<Product> findOneByName(String productName) {

        Optional<Product> productOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select c from Shop c where c.name = :name");
            query.setParameter("name", productName);
            productOp = Optional.of((Product) query.getSingleResult());
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
        return productOp;
    }

    @Override
    public Optional<Product> findGivenProduct(String productName, String categoryName, String producerName) {
        Optional<Product> productOp = Optional.empty();
        EntityManager entityManager = null;
        EntityTransaction tx = null;
        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            productOp = entityManager.createQuery("select p from Product p where p.name = :name and p.category.name = :category and p.producer.name = :producer", Product.class)
                    .setParameter("name", productName)
                    .setParameter("category", categoryName)
                    .setParameter("producer", producerName)
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
        return productOp;
    }
}
