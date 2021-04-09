package carsharing.dao;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class DAO<R> {

    private Class<R> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityClass(Class<R> entityClass) {
        this.entityClass = entityClass;
    }

    public R findById(Long id) {
        return entityManager.find(entityClass, id);
    }

    public List<R> findAll() {
        return (entityManager.createQuery("from " + entityClass.getName(), entityClass).getResultList());
    }

    public void create(R entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    public void update(R entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    public void deleteById(Long id) {
        R entity = findById(id);
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }


}
