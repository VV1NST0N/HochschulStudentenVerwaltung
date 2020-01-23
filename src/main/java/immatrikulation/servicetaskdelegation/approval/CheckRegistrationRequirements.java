package immatrikulation.servicetaskdelegation.approval;

import dataAccess.StudiengangDAO;
import entities.StudiengangEntity;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Map;

public class CheckRegistrationRequirements implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Map<String, Double> ncCourses = (Map<String, Double>) delegateExecution.getVariable("ccsOfAllCourses");
        String studiengangName = (String)  delegateExecution.getVariable("studiengangName");
        StudiengangDAO studiengangDAO = new StudiengangDAO();
        StudiengangEntity studiengangEntity = studiengangDAO.getStudiengang(studiengangName);
        Boolean zulassung = (Boolean) studiengangEntity.getVorraussetzungTest();
        Boolean nc = (Boolean) studiengangEntity.getNcNotwendig();

        System.out.println("Zahlung und NC Berechnung beendet");
        delegateExecution.setVariable("zulassungstest", zulassung);
        delegateExecution.setVariable("nc", nc);
    }
}
