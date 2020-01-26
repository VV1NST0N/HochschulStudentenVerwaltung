
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
        Boolean studentExistiert = (Boolean) delegateExecution.getVariable("studentExistiert");
        Integer matNr = (Integer) delegateExecution.getVariable("matNr");
        StudentDao studentDao = new StudentDao();
        StudentEntity studentEntity = null;
        if (!studentExistiert) {
            studentEntity = new StudentEntity();
            BewerberDAO bewerberDAO = new BewerberDAO();
            studentEntity = bewerberDAO.createStudentByBewerber(bewerberId, studentEntity);
            studentDao.insertEntity(studentEntity);
        } else {
            studentEntity = studentDao.getEntryById(matNr);
        }


        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(studiengangName);


        StudentStudiengangDao studentStudiengangDao = new StudentStudiengangDao();
        StudentStudiengangEntity studentStudiengangEntity = new StudentStudiengangEntity();
        studentStudiengangEntity.setStudentStudiengangId(IdGenerator.createUniqueIds());
        studentStudiengangEntity.setSemester(1);
        studentStudiengangEntity.setAktivesStudium(true);
        studentStudiengangEntity.setStudentEntities(studentEntity);
        studentStudiengangEntity.setStudiengangEntities(studiengangEntity);
        studentStudiengangDao.insertEntity(studentStudiengangEntity);
    }
}
