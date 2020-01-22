package dataAccess;

import entities.StudentEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class StudentDao extends Dao<StudentEntity> {

    public StudentEntity searchForStudentInDatabase(String vorname, String nachname, LocalDate gebDatum){
        EntityManager entityManager = ConnectionFac.init();


        Query query = entityManager.createQuery("SELECT p FROM StudentEntity p WHERE p.vorname = :name AND p.nachname = :nachname AND p.geburtsdatum = :geburtsdatum");
        query.setParameter("name", vorname);
        query.setParameter("nachname", nachname);
        query.setParameter("geburtsdatum", gebDatum);
        List<StudentEntity> resultList = query.getResultList();
        if(resultList.size() > 0){
            for (StudentEntity p : resultList) {
                if(p.getGeburtsdatum().equals(gebDatum) ){
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public StudentEntity getEntryById(Integer id) {
        EntityManager entityManager = ConnectionFac.init();
        StudentEntity studentEntity = entityManager.find(StudentEntity.class, id);

        return studentEntity;
    }

    @Override
    public void updateEntity(StudentEntity entity) {
        EntityManager entityManager = ConnectionFac.init();
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

}
