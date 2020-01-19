package dataAccess;

import entities.BewerberEntity;
import entities.StudiengangEntity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BewerberDAO extends Dao<StudiengangEntity> {

    public void  insertBewerber(BewerberEntity bewerberEntity, String studiengangName){
       EntityManager entityManager = ConnectionFac.init();
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        bewerberEntity.setStudiengangByStudiengangId(studiengangDAO.getStudiengang(studiengangName));
        bewerberEntity.setStudiengangId(bewerberEntity.getStudiengangByStudiengangId().getStudiengangId());

        entityManager.getTransaction().begin();
        entityManager.persist(bewerberEntity);
        entityManager.getTransaction().commit();

    }



    public List<Double> getBewerberByStudiengang(String studiengangName) {
        EntityManager em = ConnectionFac.init();
        Query query = em.createQuery("SELECT p FROM BewerberEntity p WHERE p.studiengangByStudiengangId = :name");
        query.setParameter("name", studiengangName);
        List<BewerberEntity> resultList = query.getResultList();
        List<Double> gradesList = new LinkedList<Double>();
        for (BewerberEntity p : resultList) {
            if(p.getStudiengangByStudiengangId().equals(studiengangName) ){
                gradesList.add(p.getAbiturnote());
            }
        }
        return gradesList;
    }

    @Override
    public StudiengangEntity getEntryById(Integer id) {
        EntityManager entityManager = ConnectionFac.init();
        StudiengangEntity studiengangEntity = entityManager.find(StudiengangEntity.class, id);

        return studiengangEntity;
    }
}
