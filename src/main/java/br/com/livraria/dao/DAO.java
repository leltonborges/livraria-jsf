package br.com.livraria.dao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collection;
import java.util.List;

public class DAO<T> extends HibernateDAO {
    private EntityManager em;
    private Class<T> tClass;

    public DAO(Class<T> tClass) {
        this.tClass = tClass;
    }

    public void save(T entity) {
        this.em = getManager();
        getTransitaion(this.em);
        this.em.persist(entity);
        commitAndClose();
    }

    public void saveAll(Collection<T> list) {
        for (T t : list) {
            save(t);
        }
    }

    public void remove(T entity) {
        this.em = getManager();
        getTransitaion(this.em);
        this.em.remove(this.em.merge(entity));
        commitAndClose();
    }

    public void update(T entity) {
        this.em = getManager();
        getTransitaion(this.em);
        this.em.merge(entity);
        commitAndClose();
    }

    public T getById(Integer id) {
        this.em = getManager();
        T result = em.find(tClass, id);
        closeManager(this.em);
        return  result;
    }

    public List<T> getAll() {
        List<T> list;
        this.em = getManager();
        CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(tClass);
        query.select(query.from(tClass));
        list = em.createQuery(query).getResultList();
        closeManager(this.em);
        return list;
    }

    private void commitAndClose() {
        this.getCommit(this.em);
        this.closeManager(this.em);
    }

}
