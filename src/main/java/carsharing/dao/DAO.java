package carsharing.dao;

import carsharing.dao.model.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
@Transactional
public class DAO<R> {

    private Class<R> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityClass(Class<R> entityClass) {
        this.entityClass = entityClass;
    }

    public List<R> findAll() {
        return (entityManager.createQuery("from " + entityClass.getName(), entityClass).getResultList());
    }

    public R findById(Long id) {
        return entityManager.find(entityClass, id);
    }

    public void saveOrUpdate(EntityDetails entity) {
        if(findById(entity.getId()) == null) {
            entityManager.persist(entity);
        } else {
            entityManager.merge(entity);
        }
    }

    public void delete(Long id) {
        R entity = entityManager.merge(findById(id));
        entityManager.remove(entity);
    }


    public List<Deal> getDealsByStatus(DealStatus status) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Deal> criteriaQuery = builder.createQuery(Deal.class);
        Root<Deal> root = criteriaQuery.from(Deal.class);
        criteriaQuery.select(root);

        criteriaQuery.where(builder.equal(root.get("status"), status));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Car> getAvailableCars() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> criteriaQuery = builder.createQuery(Car.class);
        Root<Car> root = criteriaQuery.from(Car.class);
        criteriaQuery.select(root);

        criteriaQuery.where(builder.equal(root.get("car_status"), CarStatus.AVAILABLE));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Customer getCustomerByEmail(String email) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);
        Root<Customer> root = criteriaQuery.from(Customer.class);
        criteriaQuery.select(root);

        criteriaQuery.where(builder.equal(root.get("email"), email));
        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

}
