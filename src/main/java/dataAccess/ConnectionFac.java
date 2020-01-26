package dataAccess;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFac {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static EntityManager init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("informationssystem");
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }
}
