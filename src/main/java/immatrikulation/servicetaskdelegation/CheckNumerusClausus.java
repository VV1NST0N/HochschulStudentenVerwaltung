package immatrikulation.servicetaskdelegation;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class CheckNumerusClausus implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String course = (String) delegateExecution.getVariable("studiengangName");
        Long grade = (Long) delegateExecution.getVariable("abiturnote");
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(course);

        if (studiengangEntity.getNumerusClaususNote() < grade){
            delegateExecution.setVariable("ncErfüllt", true);
        }else{
            delegateExecution.setVariable("ncErfüllt", false);
        }

    }
}
