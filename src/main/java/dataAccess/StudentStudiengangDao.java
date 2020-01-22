package dataAccess;

import entities.StudentStudiengangEntity;
import entities.ZahlungsstatusfremdsystemEntity;

import javax.persistence.EntityManager;

public class StudentStudiengangDao extends Dao<StudentStudiengangEntity> {

    @Override
    public StudentStudiengangEntity getEntryById(Integer id) {
        EntityManager entityManager = ConnectionFac.init();
        StudentStudiengangEntity studentStudiengangEntity = entityManager.find(StudentStudiengangEntity.class, id);
        return studentStudiengangEntity;
    }
}
