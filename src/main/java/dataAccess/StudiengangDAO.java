package dataAccess;

import entities.BewerbungsunterlagenEntity;
import entities.StudiengangEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class StudiengangDAO {

    public StudiengangEntity getStudiengang(String studiengangName) {
        EntityManager em = ConnectionFac.init();
        Query query = em.createQuery("SELECT p FROM StudiengangEntity p WHERE p.studiengangName = :name");
        query.setParameter("name", studiengangName);
        List<StudiengangEntity> resultList = query.getResultList();
        for (StudiengangEntity p : resultList) {
            if(p.getStudiengangName().equals(studiengangName) ){
                return p;
            }
        }
        return null;
    }

    public List<StudiengangEntity> getStudiengänge() {
        EntityManager em = ConnectionFac.init();
        Query query = em.createQuery("SELECT p FROM StudiengangEntity p ");
        List<StudiengangEntity> resultList = query.getResultList();

        return resultList;
    }


    public void updateCourseNc(StudiengangEntity studiengangEntity, Boolean nc) {
        EntityManager entityManager = ConnectionFac.init();

        entityManager.getTransaction().begin();
        studiengangEntity.setNcNotwendig(nc);
        entityManager.getTransaction().commit();
    }

    public void updateCourseNcNumber(StudiengangEntity studiengangEntity, Long nc) {
        EntityManager entityManager = ConnectionFac.init();

        entityManager.getTransaction().begin();
        studiengangEntity.setNumerusClaususNote(nc);
        entityManager.getTransaction().commit();
    }


}
