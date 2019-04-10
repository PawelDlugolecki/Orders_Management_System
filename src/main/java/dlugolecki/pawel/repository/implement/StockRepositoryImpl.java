package dlugolecki.pawel.repository.implement;

import dlugolecki.pawel.generic.AbstractGenericRepository;
import dlugolecki.pawel.model.entities.Stock;
import dlugolecki.pawel.repository.interfaces.StockRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Optional;

public class StockRepositoryImpl extends AbstractGenericRepository<Stock>
        implements StockRepository {

    @Override
    public Optional<Stock> findGivenStock(Long productId, Long shopId) {
        Optional<Stock> stockOptional = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            stockOptional = entityManager
                    .createQuery("select s from Stock s where s.product.id = :product and s.shop.id = :shop", Stock.class)
                    .setParameter("product", productId)
                    .setParameter("shop", shopId)
                    .getResultList().stream().findFirst();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return stockOptional;
    }

    @Override
    public Optional<Stock> findOneStock(String productName, String categoryName, String producerName, String shopName, String shopCountryName) {

        Optional<Stock> stockOptional = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();


            stockOptional = entityManager
                    .createQuery("select s from Stock s where s.product.name = :productName and s.product.category.name = :categoryName and s.product.producer.name = :producerName and s.shop.name = :shopName and s.shop.country.name = :shopCountryName", Stock.class)
                    .setParameter("productName", productName)
                    .setParameter("categoryName", categoryName)
                    .setParameter("producerName", producerName)
                    .setParameter("shopName", shopName)
                    .setParameter("shopCountryName", shopCountryName)
                    .getResultList().stream().findFirst();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return stockOptional;
    }
}

