package kkg.uber.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class AbstractDAO<E, I extends Serializable>{

    private Class<E> entityClass;

    protected AbstractDAO(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Qualifier("sessionFactory")
    @Autowired
    private SessionFactory sessionFactory;



    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    public E findById(I id) {
        return (E) getCurrentSession().get(entityClass, id);
    }

    public void saveOrUpdate(E e) {
        getCurrentSession().saveOrUpdate(e);
        //getCurrentSession().close();
    }

    public void save(E e) {
        getCurrentSession().save(e);
        //getCurrentSession().close();
    }

    public void delete(E e) {
        getCurrentSession().delete(e);
    }

    public List<E> findByCriteria(Criterion criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        return criteria.list();
    }

    public List<E> findByCriteria(Criterion criterion, Integer count) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);

        if(count != null){
            criteria.setMaxResults(count);
        }
        return criteria.list();
    }

    public E findByCriterionID(Criterion criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        return (E) criteria.uniqueResult();
    }

    public List<E> findAllByKey(String key, Criterion criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        criteria.setProjection(Projections.distinct(Projections.property(key)));
        return criteria.list();
    }

    public List<E> getMinOrderByCriteria(Criterion criterion, String orderBy) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        criteria.addOrder(Order.asc(orderBy));
        criteria.setMaxResults(1);

        return criteria.list();
    }

    public List<E> findUniqueByCriteria(Criterion criterion, String uniqueField) {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        criteria.add(criterion);
        criteria.setProjection(Projections.distinct(Projections.property(uniqueField)));
        return criteria.list();
    }


    public List<E> getAll() {
        Criteria criteria = getCurrentSession().createCriteria(entityClass);
        return criteria.list();
    }
}

