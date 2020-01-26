package dataAccess;

import entities.StudentEntity;
import entities.StudentStudiengangEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class StudentStudiengangDao extends Dao<StudentStudiengangEntity> {

    @Override
    public StudentStudiengangEntity getEntryById(Integer id) {
        EntityManager entityManager = ConnectionFac.init();
        StudentStudiengangEntity studentStudiengangEntity = entityManager.find(StudentStudiengangEntity.class, id);
        return studentStudiengangEntity;
    }

    public List<StudentStudiengangEntity> searchForStudentInDatabase(Integer matNr) {
        EntityManager entityManager = ConnectionFac.init();

        Query query = entityManager.createQuery("SELECT p FROM StudentStudiengangEntity p WHERE p.studentEntities.matNr = :matNr");
        query.setParameter("matNr", matNr);
        List<StudentStudiengangEntity> resultList = query.getResultList();

        return resultList;
    }
}
