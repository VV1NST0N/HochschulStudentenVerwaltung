package dataAccess;

import dataAccess.Exception.CustomBewerberException;
import entities.BewerberEntity;
import entities.StudentEntity;
import entities.StudiengangEntity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BewerberDAO extends Dao<BewerberEntity> {

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
    public BewerberEntity getEntryById(Integer id) {
        EntityManager entityManager = ConnectionFac.init();
        BewerberEntity bewerberEntity = entityManager.find(BewerberEntity.class, id);

        return bewerberEntity;
    }

    @Override
    public void updateEntity(BewerberEntity entity) {
        EntityManager entityManager = ConnectionFac.init();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    public List<Integer> getBewerberIds() {

        EntityManager em = ConnectionFac.init();
        Query query = em.createQuery("SELECT c FROM BewerberEntity c");
        List<BewerberEntity> resultList = query.getResultList();
        List<Integer> bewerberIds = new ArrayList<>();
        for (BewerberEntity b: resultList) {
            bewerberIds.add(b.getBewerberId());
        }

        return bewerberIds;
    }

    public BewerberEntity getApplicantIfAlreadyExistent(String vorname, String nachname, LocalDate geburtsdatum, String wohnort) throws CustomBewerberException {
        EntityManager em = ConnectionFac.init();
        Query query = em.createQuery("SELECT c FROM BewerberEntity c");
        List<BewerberEntity> resultList = query.getResultList();

        if (resultList.size() == 1) {
            return resultList.get(0);
        }else if(resultList.size() > 1){
            throw new CustomBewerberException();
        }else {
            return null;
        }

    }

    public StudentEntity createStudentByBewerber(Integer bewerberId, StudentEntity studentEntity) {
        BewerberEntity bewerberEntity = getEntryById(bewerberId);
        studentEntity.setMatNr(bewerberEntity.getMatNr());
        studentEntity.setAdresse(bewerberEntity.getAdresse());
        studentEntity.setEmail(bewerberEntity.getEmail());
        studentEntity.setGeburtsdatum(bewerberEntity.getGeburtsdatum());
        studentEntity.setGeburtsort(bewerberEntity.getGeburtsort());
        studentEntity.setNachname(bewerberEntity.getNachname());
        studentEntity.setVorname(bewerberEntity.getVorname());
        studentEntity.setWohnort(bewerberEntity.getWohnort());
        return studentEntity;
    }
}
