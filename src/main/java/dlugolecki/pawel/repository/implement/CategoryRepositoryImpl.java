package dlugolecki.pawel.repository.implement;

import dlugolecki.pawel.generic.AbstractGenericRepository;
import dlugolecki.pawel.model.entities.Category;
import dlugolecki.pawel.repository.interfaces.CategoryRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.Optional;

public class CategoryRepositoryImpl extends AbstractGenericRepository<Category>
        implements CategoryRepository {

    @Override
    public Optional<Category> findOneByName(String name) {

        Optional<Category> categoryOp = Optional.empty();

        EntityManager entityManager = null;
        EntityTransaction tx = null;

        try {
            entityManager = getEntityManagerFactory().createEntityManager();
            tx = entityManager.getTransaction();
            tx.begin();
            Query query = entityManager.createQuery("select c from Category c where c.name = :name");
            query.setParameter("name", name);
            categoryOp = Optional.of((Category) query.getSingleResult());
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
        return categoryOp;
    }
}