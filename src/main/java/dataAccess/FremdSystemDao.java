package dataAccess;

import entities.StudentEntity;
import entities.ZahlungsstatusfremdsystemEntity;

import javax.persistence.EntityManager;
import java.util.Date;

public class FremdSystemDao extends Dao<ZahlungsstatusfremdsystemEntity> {
    @Override
    public ZahlungsstatusfremdsystemEntity getEntryById(Integer id) {
        EntityManager entityManager = ConnectionFac.init();
        ZahlungsstatusfremdsystemEntity zahlung = entityManager.find(ZahlungsstatusfremdsystemEntity.class, id);
        return zahlung;
    }


    public void insertZahlungssystemEntry(ZahlungsstatusfremdsystemEntity zahlung){
        EntityManager entityManager = ConnectionFac.init();
        entityManager.getTransaction().begin();
        entityManager.persist(zahlung);
        entityManager.getTransaction().commit();
    }

}
