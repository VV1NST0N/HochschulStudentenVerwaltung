package immatrikulation.servicetaskdelegation;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class CheckNumerusClausus implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String course = (String) delegateExecution.getVariable("ccsOfAllCourses");
        Map<String, Double> grades = (Map<String, Double>) delegateExecution.getVariable("abiturnote");
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(course);

        if (studiengangEntity.getNumerusClaususNote() < grades.get(course)){
            delegateExecution.setVariable("ncErfüllt", true);
        }else{
            delegateExecution.setVariable("ncErfüllt", false);
        }

    }
}
