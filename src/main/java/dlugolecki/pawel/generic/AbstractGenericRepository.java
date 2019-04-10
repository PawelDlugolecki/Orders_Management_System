package dlugolecki.pawel.generic;

import dlugolecki.pawel.exception.MyException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractGenericRepository<T> implements GenericRepository<T> {

    private EntityManagerFactory entityManagerFactory =
            DbConnection.getInstance()
                    .getEntityManagerFactory();

    private Class<T> entityClass =
            (Class<T>) ((ParameterizedType) this.getClass()
                    .getGenericSuperclass())
                    .getActualTypeArguments()[0];

    protected EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    @Override
    public void add(T t) {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory
                    .createEntityManager();
            transaction = entityManager
                    .getTransaction();

            transaction.begin();
            entityManager.merge(t);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("ABSTRACT_GENERIC_REPOSITORY", "Add");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public void delete(Long id) {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();
            T t = entityManager.find(entityClass, id);
            entityManager.remove(t);
            transaction.commit();

        } catch (MyException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("ABSTRACT_GENERIC_REPOSITORY", "Delete");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public Optional<T> findOneById(Long id) {

        Optional<T> optional = Optional.empty();
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();
            optional = Optional
                    .of(entityManager.find(entityClass, id));
            transaction.commit();

        } catch (MyException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("ABSTRACT_GENERIC_REPOSITORY", "Find one");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return optional;
    }

    @Override
    public List<T> findAll() {

        List<T> items = new ArrayList<>();
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();
            Query query = entityManager.createQuery(
                    "select t from " + entityClass.getCanonicalName() + " t");
            items = query.getResultList();
            transaction.commit();

        } catch (MyException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("ABSTRACT_GENERIC_REPOSITORY", "Find all");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return items;
    }

    @Override
    public void deleteAll() {

        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();

            transaction.begin();

            Query query = entityManager.createQuery(
                    "select t from " + entityClass.getCanonicalName() + " t");
            List<T> items = query.getResultList();

            for (T item : items) {
                entityManager.remove(item);
            }
            transaction.commit();

        } catch (MyException e) {
            throw new MyException("ABSTRACT_GENERIC_REPOSITORY", "Delete all");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
