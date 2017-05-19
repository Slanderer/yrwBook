package yrw.ejb;

import yrw.model.AbstractEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;


@Stateless
public class BaseEJB implements Serializable {

    @PersistenceContext
    protected EntityManager em;

    public <E extends AbstractEntity> E findById(Class<E> clazz, long id) {
        return em.find(clazz, id);
    }

    public <E extends AbstractEntity> List<E> fetchAll(Class<E> clazz) {
        return em.createQuery("from " + clazz.getName() + " c", clazz).getResultList();
    }

    public <E extends AbstractEntity> List<E> fetchAll(Class<E> clazz, String orderByField) {
        return fetchAll(clazz, orderByField, false);
    }

    public <E extends AbstractEntity> List<E> fetchAll(Class<E> clazz, String orderByField, boolean desc) {
        return em.createQuery("from " + clazz.getName() + " c order by c." + orderByField + (desc ? " desc" : ""), clazz).getResultList();
    }

    public <E extends AbstractEntity> E updateEntity(E entity) {
        return em.merge(entity);
    }

    public <E extends AbstractEntity> void createEntity(E entity) {
        em.persist(entity);
    }

    public <E extends AbstractEntity> void deleteEntity(E entity) {
        em.remove(em.find(entity.getClass(), entity.getId()));
    }


}
