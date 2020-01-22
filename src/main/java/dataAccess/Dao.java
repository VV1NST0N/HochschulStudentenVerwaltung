package dataAccess;

import entities.ImmatrikulationsverfahrenStatusEntity;

import javax.persistence.EntityManager;

public class Dao<T> implements DaoTemplate {

    public void updateEntity(T entity){

        EntityManager entityManager = ConnectionFac.init();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    public  void insertEntity(T entity){
        EntityManager entityManager = ConnectionFac.init();
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    public void deleteEntry(T entity){
        EntityManager entityManager = ConnectionFac.init();
        if (!entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        }
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public T getEntryById(Integer id) {
        return null;
    }
}
