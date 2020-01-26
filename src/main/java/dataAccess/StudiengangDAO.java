package dataAccess;

import entities.StudiengangEntity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class StudiengangDAO extends Dao<StudiengangEntity>{

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


    @Override
    public StudiengangEntity getEntryById(Integer id) {
        EntityManager entityManager = ConnectionFac.init();
        StudiengangEntity studiengangEntity = entityManager.find(StudiengangEntity.class, id);

        return studiengangEntity;
    }

}
