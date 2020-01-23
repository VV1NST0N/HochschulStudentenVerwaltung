
package immatrikulation.servicetaskdelegation.studentCreation;

import dataAccess.BewerberDAO;
import dataAccess.StudentDao;
import dataAccess.StudentStudiengangDao;
import dataAccess.StudiengangDAO;
import entities.StudentEntity;
import entities.StudentStudiengangEntity;
import entities.StudiengangEntity;
import helper.IdGenerator;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CreateStudent implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Integer bewerberId = (Integer) delegateExecution.getVariable("bewerberId");
        String studiengangName = (String) delegateExecution.getVariable("studiengangName");

        StudentDao studentDao = new StudentDao();
        StudentEntity studentEntity = new StudentEntity();
        BewerberDAO bewerberDAO = new BewerberDAO();
        studentEntity = bewerberDAO.createStudentByBewerber(bewerberId, studentEntity);
        studentDao.insertEntity(studentEntity);

        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(studiengangName);


        StudentStudiengangDao studentStudiengangDao = new StudentStudiengangDao();
        StudentStudiengangEntity studentStudiengangEntity = new StudentStudiengangEntity();
        studentStudiengangEntity.setStudentStudiengangId(IdGenerator.createUniqueIds());
        studentStudiengangEntity.setSemeser(1);
        studentStudiengangEntity.setAktivesStudium(true);
        studentStudiengangEntity.setStudentEntities(studentEntity);
        studentStudiengangEntity.setStudiengangEntities(studiengangEntity);
        studentStudiengangDao.insertEntity(studentStudiengangEntity);
    }
}
