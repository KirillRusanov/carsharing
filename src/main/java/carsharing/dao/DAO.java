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

    public R findById(Long id) {
        return entityManager.find(entityClass, id);
    }

    public List<R> findAll() {
        return (entityManager.createQuery("from " + entityClass.getName(), entityClass).getResultList());
    }

    public void create(R entity) {
        entityManager.persist(entity);
    }

    public void update(R entity) {
        entityManager.merge(entity);
    }

    public void deleteById(Long id) {
        R entity = findById(id);
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

    public Role findRoleByName(String name) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Role> criteriaQuery = builder.createQuery(Role.class);
        Root<Role> root = criteriaQuery.from(Role.class);
        criteriaQuery.select(root);

        criteriaQuery.where(builder.equal(root.get("name"), name));
        try {
            return entityManager.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }

    }


}
